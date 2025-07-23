package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.Organization;

public record OrganizationAddressResponse(
        String street,
        String city,
        String state,
        String country
) {

    public static OrganizationAddressResponse from(Organization organization) {
        return new OrganizationAddressResponse(
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getCountry()
        );
    }

}
