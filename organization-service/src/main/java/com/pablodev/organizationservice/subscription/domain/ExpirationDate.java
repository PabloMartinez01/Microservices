package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.ValueObject;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ExpirationDate extends ValueObject<LocalDate> {

    public ExpirationDate(LocalDate value) {
        super(value);
    }
}
