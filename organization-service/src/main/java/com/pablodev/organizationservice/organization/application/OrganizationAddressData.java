package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class OrganizationAddressData {

    private final String street;
    private final String city;
    private final String state;
    private final String country;

    public static OrganizationAddressData from(Organization organization) {
        return new OrganizationAddressData(
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getCountry()
        );
    }

}
