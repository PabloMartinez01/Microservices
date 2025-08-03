package com.pablodev.organizationservice.organization.domain;

import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class OrganizationAddress {

    private final String street;
    private final String city;
    private final String state;
    private final String country;

    public OrganizationAddress(String street, String city, String state, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        ensureValidFields();
    }

    private static void ensureNotBlank(String field, String message) {
        if (field == null || field.trim().isBlank()) {
            throw new OrganizationIllegalArgumentException(message);
        }
    }

    private void ensureValidFields() {
        ensureNotBlank(street, "Street can not be empty");
        ensureNotBlank(city, "City can not be empty");
        ensureNotBlank(state, "State can not be empty");
        ensureNotBlank(country, "Country can not be empty");
    }

}
