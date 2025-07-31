package com.pablodev.organizationservice.subscription.application.find_by_organization;

import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.shared.domain.query.QueryResponse;
import java.time.LocalDate;

public record SubscriptionResponse(
        String id,
        String organizationId,
        LocalDate startDate,
        LocalDate expirationDate,
        Boolean cancelled) implements QueryResponse {


    public static SubscriptionResponse fromAggregate(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getIdValue(),
                subscription.getOrganizationIdValue(),
                subscription.getStartDate(),
                subscription.getExpirationDate(),
                subscription.isCancelled()
        );
    }

}
