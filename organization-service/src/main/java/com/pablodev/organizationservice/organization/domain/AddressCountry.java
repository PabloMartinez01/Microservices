package com.pablodev.organizationservice.organization.domain;

import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import com.pablodev.shared.domain.ValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class AddressCountry extends ValueObject<String> {

    public AddressCountry(String value) {
        super(value);
        ensureValidCountry();
    }

    private void ensureValidCountry() {
        if (value == null || value.trim().isBlank()) {
            throw new OrganizationIllegalArgumentException("Country can not be empty");
        }
    }
}
