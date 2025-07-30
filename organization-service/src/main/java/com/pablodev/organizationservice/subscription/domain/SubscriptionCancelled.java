package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.ValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class SubscriptionCancelled extends ValueObject<Boolean> {

    public SubscriptionCancelled(Boolean value) {
        super(value);
    }

}
