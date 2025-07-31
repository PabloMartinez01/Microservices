package com.pablodev.organizationservice.subscription.application;


import com.pablodev.shared.domain.query.QueryResponse;
import java.util.List;

public record SubscriptionsResponse(List<SubscriptionResponse> subscriptions)
        implements QueryResponse {

}
