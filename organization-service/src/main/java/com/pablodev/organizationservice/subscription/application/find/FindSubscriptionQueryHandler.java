package com.pablodev.organizationservice.subscription.application.find;

import com.pablodev.organizationservice.subscription.application.SubscriptionResponse;
import com.pablodev.shared.domain.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindSubscriptionQueryHandler implements
        QueryHandler<FindSubscriptionQuery, SubscriptionResponse> {

    private final SubscriptionFinder finder;

    @Override
    public SubscriptionResponse handle(FindSubscriptionQuery query) {
        return finder.findById(query.id());
    }
}
