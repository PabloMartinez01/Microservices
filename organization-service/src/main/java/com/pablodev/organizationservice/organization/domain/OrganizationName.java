package com.pablodev.organizationservice.organization.domain;

import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import com.pablodev.shared.domain.ValueObject;
import lombok.Getter;

@Getter
public class OrganizationName extends ValueObject<String> {

    public OrganizationName(String value) {
        super(value);
        ensureNameIsValid();
    }

    private void ensureNameIsValid() {
        if (value == null || value.isBlank()) {
            throw new OrganizationIllegalArgumentException("The name can not be null or blank");
        }
    }

}
