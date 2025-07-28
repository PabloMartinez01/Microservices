package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.organizationservice.organization.domain.AddressCityMother;
import com.pablodev.organizationservice.organization.domain.AddressCountryMother;
import com.pablodev.organizationservice.organization.domain.AddressStateMother;
import com.pablodev.organizationservice.organization.domain.AddressStreetMother;
import com.pablodev.organizationservice.organization.domain.OrganizationIdMother;
import com.pablodev.organizationservice.organization.domain.OrganizationNameMother;
import com.pablodev.organizationservice.organization.domain.OrganizationTypeMother;

public class CreateOrganizationCommandBuilder {

    private String id;
    private String name;
    private String type;
    private String street;
    private String city;
    private String state;
    private String country;

    public CreateOrganizationCommandBuilder() {
        id = OrganizationIdMother.random().getValue();
        name = OrganizationNameMother.random().getValue();
        type = OrganizationTypeMother.random().name();
        street = AddressStreetMother.random().getValue();
        city = AddressCityMother.random().getValue();
        state = AddressStateMother.random().getValue();
        country = AddressCountryMother.random().getValue();
    }

    public static CreateOrganizationCommandBuilder withRandomValues() {
        return new CreateOrganizationCommandBuilder();
    }

    public CreateOrganizationCommandBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CreateOrganizationCommandBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CreateOrganizationCommandBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CreateOrganizationCommandBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public CreateOrganizationCommandBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public CreateOrganizationCommandBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public CreateOrganizationCommandBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public CreateOrganizationCommand build() {
        return new CreateOrganizationCommand(id, name, type, street, city, state, country);
    }


}
