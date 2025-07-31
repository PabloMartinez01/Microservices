package com.pablodev.organizationservice.subscription.infrastructure.persistence;

import com.pablodev.organizationservice.subscription.domain.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionEntityMapper {

    public SubscriptionEntity toEntity(Subscription subscription) {
        return new SubscriptionEntity(
                subscription.getIdValue(),
                subscription.getOrganizationIdValue(),
                subscription.getStartDate(),
                subscription.getExpirationDate(),
                subscription.isCancelled()
        );
    }

    public Subscription toAggregate(SubscriptionEntity entity) {
        return new Subscription(
                entity.getId(),
                entity.getOrganizationId(),
                entity.getStartDate(),
                entity.getExpirationDate(),
                entity.getCancelled()
        );
    }

}
