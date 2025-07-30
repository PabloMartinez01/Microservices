package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.ValueObject;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class StartDate extends ValueObject<LocalDate> {

    public StartDate(LocalDate value) {
        super(value);
    }
}
