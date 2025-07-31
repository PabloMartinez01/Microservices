package com.pablodev.organizationservice.subscription.application;

import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.shared.domain.query.QueryResponse;
import java.time.LocalDate;

public record SubscriptionResponse(
        String id,
        String organizationId,
        LocalDate startDate,
        LocalDate expirationDate,
        String status
) implements QueryResponse {


    public static SubscriptionResponse fromAggregate(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getIdValue(),
                subscription.getOrganizationIdValue(),
                subscription.getStartDate(),
                subscription.getExpirationDate(),
                subscription.getStatus().name()
        );
    }

}
