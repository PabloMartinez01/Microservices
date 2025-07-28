package com.pablodev.organizationservice.organization.domain;

public class OrganizationMother {


    public static Organization create(String id, String name, String type, String street,
            String city, String state, String country) {
        return new Organization(id, name, type, street, city, state, country);
    }
    

    public static Organization random() {
        return create(
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
