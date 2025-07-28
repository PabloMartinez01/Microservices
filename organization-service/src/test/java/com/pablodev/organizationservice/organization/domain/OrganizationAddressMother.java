package com.pablodev.organizationservice.organization.domain;

public class OrganizationAddressMother {

    public static OrganizationAddress random() {
        OrganizationBuilder.withRandomValues();
        return new OrganizationAddress(
                AddressStreetMother.random().getValue(),
                AddressCityMother.random().getValue(),
                AddressStateMother.random().getValue(),
                AddressCountryMother.random().getValue()
        );
    }

    public static OrganizationAddress create(String street, String city, String state,
            String country) {
        return new OrganizationAddress(
                AddressStreetMother.create(street).getValue(),
                AddressCityMother.create(city).getValue(),
                AddressStateMother.create(state).getValue(),
                AddressCountryMother.create(country).getValue()
        );
    }

}
