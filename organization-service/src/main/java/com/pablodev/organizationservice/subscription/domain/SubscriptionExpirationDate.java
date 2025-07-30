package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.ValueObject;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class SubscriptionExpirationDate extends ValueObject<LocalDate> {

    public SubscriptionExpirationDate(LocalDate value) {
        super(value);
    }
}
