package com.pablodev.organizationservice.subscription.application.create;

import com.pablodev.organizationservice.organization.application.find.OrganizationFinder;
import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.organizationservice.subscription.domain.SubscriptionRepository;
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

        Subscription subscription = Subscription.create(
                command.id(),
                command.organizationId(),
                command.startDate(),
                command.expirationDate());

        subscriptionRepository.save(subscription);

    }

    private void ensureOrganizationExists(String id) {
        organizationFinder.find(id);
    }


}
