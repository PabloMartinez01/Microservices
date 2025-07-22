package com.pablodev.organizationservice.organization.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrganizationAddress {
    private final AddressStreet street;
    private final AddressCity city;
    private final AddressState state;
    private final AddressCountry country;

    public static OrganizationAddress create(String street, String city, String state, String country) {
        return new OrganizationAddress(
                new AddressStreet(street),
                new AddressCity(city),
                new AddressState(state),
                new AddressCountry(country)
        );
    }

}
