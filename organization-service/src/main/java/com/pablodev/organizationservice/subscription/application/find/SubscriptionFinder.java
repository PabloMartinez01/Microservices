package com.pablodev.organizationservice.subscription.application.find;

import com.pablodev.organizationservice.subscription.application.SubscriptionResponse;
import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.organizationservice.subscription.domain.SubscriptionId;
import com.pablodev.organizationservice.subscription.domain.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionFinder {

    private final SubscriptionRepository repository;

    public SubscriptionResponse findById(String id) {
        Subscription subscription = repository.findById(new SubscriptionId(id));
        return SubscriptionResponse.fromAggregate(subscription);
    }

}
