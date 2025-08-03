package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.organizationservice.organization.application.OrganizationAddressData;
import com.pablodev.organizationservice.organization.domain.OrganizationAddress;
import com.pablodev.organizationservice.organization.domain.OrganizationAddressMother;
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
        OrganizationAddress address = OrganizationAddressMother.random();
        id = OrganizationIdMother.random().getValue();
        name = OrganizationNameMother.random().getValue();
        type = OrganizationTypeMother.random().name();
        street = address.getStreet();
        city = address.getCity();
        state = address.getState();
        country = address.getCountry();
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
        return new CreateOrganizationCommand(id, name, type,
                new OrganizationAddressData(street, city, state, country));
    }


}
