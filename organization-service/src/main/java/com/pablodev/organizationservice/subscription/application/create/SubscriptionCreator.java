package com.pablodev.organizationservice.subscription.application.create;

import com.pablodev.organizationservice.organization.application.find.OrganizationFinder;
import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.organizationservice.subscription.domain.SubscriptionRepository;
import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionDateRangeOverlapException;
import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.Order;
import java.time.LocalDate;
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
        ensureSubscriptionsNotOverlap(command.startDate(), command.expirationDate(),
                command.organizationId());

        Subscription subscription = Subscription.create(
                command.id(),
                command.organizationId(),
                command.startDate(),
                command.expirationDate());

        subscriptionRepository.save(subscription);

    }

    private void ensureSubscriptionsNotOverlap(LocalDate startDate, LocalDate endDate,
            String organizationId) {

        Criteria byOrganizationId = Criteria.of(
                Order.unordered(),
                Filter.equals("organizationId", organizationId)
        );

        for (Subscription subscription : subscriptionRepository.search(byOrganizationId)) {
            if (subscription.overlaps(startDate, endDate)) {
                throw new SubscriptionDateRangeOverlapException(startDate, endDate);
            }
        }

    }

    private void ensureOrganizationExists(String id) {
        organizationFinder.find(id);
    }


}
