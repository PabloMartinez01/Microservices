package com.pablodev.organizationservice.subscription.application.find;

import com.pablodev.organizationservice.subscription.application.SubscriptionResponse;
import com.pablodev.shared.domain.query.Query;

public record FindSubscriptionQuery(String id) implements Query<SubscriptionResponse> {

}
