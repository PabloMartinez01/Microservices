package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.ValueObject;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class SubscriptionStartDate extends ValueObject<LocalDate> {

    public SubscriptionStartDate(LocalDate value) {
        super(value);
    }
}
