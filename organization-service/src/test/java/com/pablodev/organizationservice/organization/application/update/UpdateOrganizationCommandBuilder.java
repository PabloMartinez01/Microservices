package com.pablodev.organizationservice.organization.application.update;

import com.pablodev.organizationservice.organization.domain.AddressCityMother;
import com.pablodev.organizationservice.organization.domain.AddressCountryMother;
import com.pablodev.organizationservice.organization.domain.AddressStateMother;
import com.pablodev.organizationservice.organization.domain.AddressStreetMother;
import com.pablodev.organizationservice.organization.domain.OrganizationIdMother;
import com.pablodev.organizationservice.organization.domain.OrganizationNameMother;
import com.pablodev.organizationservice.organization.domain.OrganizationTypeMother;

public class UpdateOrganizationCommandBuilder {

    private String id;
    private String name;
    private String type;
    private String street;
    private String city;
    private String state;
    private String country;

    public UpdateOrganizationCommandBuilder() {
        id = OrganizationIdMother.random().getValue();
        name = OrganizationNameMother.random().getValue();
        type = OrganizationTypeMother.random().name();
        street = AddressStreetMother.random().getValue();
        city = AddressCityMother.random().getValue();
        state = AddressStateMother.random().getValue();
        country = AddressCountryMother.random().getValue();
    }

    public static UpdateOrganizationCommandBuilder withRandomValues() {
        return new UpdateOrganizationCommandBuilder();
    }

    public UpdateOrganizationCommandBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UpdateOrganizationCommandBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UpdateOrganizationCommandBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public UpdateOrganizationCommandBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public UpdateOrganizationCommandBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public UpdateOrganizationCommandBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public UpdateOrganizationCommandBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public UpdateOrganizationCommand build() {
        return new UpdateOrganizationCommand(id, name, type, street, city, state, country);
    }

}
