package com.pablodev.organizationservice.organization.domain;

import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import com.pablodev.shared.domain.ValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class AddressCity extends ValueObject<String> {

    public AddressCity(String value) {
        super(value);
        ensureValidCity();
    }

    private void ensureValidCity() {
        if (value == null || value.trim().isBlank()) {
            throw new OrganizationIllegalArgumentException("City can not be empty");
        }
    }
}
