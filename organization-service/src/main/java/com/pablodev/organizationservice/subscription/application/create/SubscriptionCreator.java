package com.pablodev.organizationservice.subscription.application.create;

import com.pablodev.organizationservice.organization.application.find.OrganizationFinder;
import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.organizationservice.subscription.domain.SubscriptionRepository;
import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionDateRangeOverlapException;
import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionCreator {

    private final SubscriptionRepository subscriptionRepository;
    private final OrganizationFinder organizationFinder;

    public void create(CreateSubscriptionCommand command) {

        ensureOrganizationExists(command.organizationId());
        ensureSubscriptionsNotOverlap(command.organizationId());

        Subscription subscription = Subscription.create(command.id(), command.organizationId());

        subscriptionRepository.save(subscription);
    }

    private void ensureSubscriptionsNotOverlap(String organizationId) {

        Criteria byOrganizationAndExpirationDate = Criteria.of(
                Order.unordered(),
                Filter.equals("organizationId", organizationId),
                Filter.greaterThan("expirationDate",
                        LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE))
        );

        if (!subscriptionRepository.search(byOrganizationAndExpirationDate).isEmpty()) {
            throw new SubscriptionDateRangeOverlapException();
        }

    }

    private void ensureOrganizationExists(String id) {
        organizationFinder.find(id);
    }


}
