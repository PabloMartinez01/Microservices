package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionIllegalArgumentException;
import com.pablodev.shared.domain.ValueObject;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class SubscriptionStartDate extends ValueObject<LocalDate> {

    public SubscriptionStartDate(LocalDate value) {
        super(value);
        ensureNotNull();
    }

    private void ensureNotNull() {
        if (value == null) {
            throw new SubscriptionIllegalArgumentException("Start date cannot be null");
        }
    }


    public boolean isBeforeNow() {
        return value.isBefore(LocalDate.now());
    }

    public boolean isAfterNow() {
        return value.isAfter(LocalDate.now());
    }


}
