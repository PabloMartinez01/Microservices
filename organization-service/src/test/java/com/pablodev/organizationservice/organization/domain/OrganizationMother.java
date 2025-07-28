package com.pablodev.organizationservice.organization.domain;

public class OrganizationMother {

    public static Organization random() {
        return new Organization(
                OrganizationIdMother.random().getValue(),
                OrganizationNameMother.random().getValue(),
                OrganizationTypeMother.random().name(),
                AddressStreetMother.random().getValue(),
                AddressCityMother.random().getValue(),
                AddressStateMother.random().getValue(),
                AddressCountryMother.random().getValue()
        );
    }

}
