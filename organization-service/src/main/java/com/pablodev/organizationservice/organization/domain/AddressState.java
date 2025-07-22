package com.pablodev.organizationservice.organization.domain;

import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import com.pablodev.shared.domain.ValueObject;

public class AddressState extends ValueObject<String> {
    public AddressState(String value) {
        super(value);
        ensureValidState();
    }

    private void ensureValidState() {
        if (value == null || value.trim().isBlank()) {
            throw new OrganizationIllegalArgumentException("State can not be empty");
        }
    }
}
