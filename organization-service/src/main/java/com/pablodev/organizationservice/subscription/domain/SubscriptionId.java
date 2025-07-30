package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.Identifier;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class SubscriptionId extends Identifier {

    public SubscriptionId(String id) {
        super(id);
    }

}
