package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.criteria.Criteria;
import java.util.List;

public interface SubscriptionRepository {

    void save(Subscription subscription);

    Subscription findById(SubscriptionId subscriptionId);

    List<Subscription> searchBy(Criteria criteria);

    void deleteById(SubscriptionId subscriptionId);
}
