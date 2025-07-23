package com.pablodev.organizationservice.organization.domain;

import com.pablodev.organizationservice.organization.domain.exception.UnknownOrganizationTypeException;

public enum OrganizationType {
    COMPANY, INDIVIDUAL;

    public static OrganizationType from(String value) {
        try {
            return OrganizationType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException _) {
            throw new UnknownOrganizationTypeException(value);
        }
    }

}
