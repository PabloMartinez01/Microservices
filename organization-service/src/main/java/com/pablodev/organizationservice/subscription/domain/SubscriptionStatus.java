package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.ValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class SubscriptionStatus extends ValueObject<Boolean> {

    public SubscriptionStatus(Boolean value) {
        super(value);
    }
}
