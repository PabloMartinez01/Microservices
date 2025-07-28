package com.pablodev.organizationservice.organization.domain;

public class OrganizationAddressMother {

    private static OrganizationAddress random() {
        return new OrganizationAddress(
                AddressStreetMother.random().getValue(),
                AddressCityMother.random().getValue(),
                AddressStateMother.random().getValue(),
                AddressCountryMother.random().getValue()
        );
    }

}
