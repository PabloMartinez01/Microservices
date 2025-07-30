package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionIllegalArgumentException;
import com.pablodev.shared.domain.ValueObject;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class SubscriptionExpirationDate extends ValueObject<LocalDate> {

    public SubscriptionExpirationDate(LocalDate value) {
        super(value);
        ensureNotNull();
    }

    private void ensureNotNull() {
        if (value == null) {
            throw new SubscriptionIllegalArgumentException("Expiration date cannot be null");
        }
    }


}
