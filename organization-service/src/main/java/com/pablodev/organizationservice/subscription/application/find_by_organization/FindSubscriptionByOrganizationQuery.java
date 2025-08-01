package com.pablodev.organizationservice.subscription.application.find_by_organization;

import com.pablodev.organizationservice.subscription.application.SubscriptionsResponse;
import com.pablodev.shared.domain.query.Query;

public record FindSubscriptionByOrganizationQuery(String organizationId)
        implements Query<SubscriptionsResponse> {

}
