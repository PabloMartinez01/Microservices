package com.pablodev.organizationservice.organization.domain;

import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import com.pablodev.shared.domain.ValueObject;
import lombok.Getter;

@Getter
public class AddressStreet extends ValueObject<String> {

    public AddressStreet(String value) {
        super(value);
        ensureValidStreet();
    }

    private void ensureValidStreet() {
        if (value == null || value.trim().isBlank()) {
            throw new OrganizationIllegalArgumentException("Street can not be empty");
        }
    }

}
