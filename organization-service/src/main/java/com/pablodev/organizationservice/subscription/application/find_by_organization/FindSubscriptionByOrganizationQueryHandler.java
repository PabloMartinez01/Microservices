package com.pablodev.organizationservice.subscription.application.find_by_organization;

import com.pablodev.organizationservice.subscription.application.SubscriptionsResponse;
import com.pablodev.shared.domain.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindSubscriptionByOrganizationQueryHandler implements
        QueryHandler<FindSubscriptionByOrganizationQuery, SubscriptionsResponse> {

    private final SubscriptionFinderByOrganization finder;

    @Override
    public SubscriptionsResponse handle(FindSubscriptionByOrganizationQuery query) {
        return finder.findByOrganization(query.organizationId());
    }
}
