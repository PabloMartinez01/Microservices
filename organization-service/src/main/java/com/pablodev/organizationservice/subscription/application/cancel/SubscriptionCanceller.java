package com.pablodev.organizationservice.subscription.application.cancel;

import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.organizationservice.subscription.domain.SubscriptionId;
import com.pablodev.organizationservice.subscription.domain.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionCanceller {

    private final SubscriptionRepository repository;

    public void cancel(String id) {
        Subscription subscription = repository.findById(new SubscriptionId(id));
        subscription.cancel();
        repository.save(subscription);
    }

}
