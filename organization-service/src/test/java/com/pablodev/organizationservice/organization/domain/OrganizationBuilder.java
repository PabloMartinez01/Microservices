package com.pablodev.organizationservice.organization.domain;

public class OrganizationBuilder {

    private String id;
    private String name;
    private String type;
    private String street;
    private String city;
    private String state;
    private String country;

    public OrganizationBuilder() {

        OrganizationAddress address = OrganizationAddressMother.random();

        id = OrganizationIdMother.random().getValue();
        name = OrganizationNameMother.random().getValue();
        type = OrganizationTypeMother.random().name();
        street = address.getStreet();
        city = address.getCity();
        state = address.getState();
        country = address.getCountry();
    }

    public static OrganizationBuilder withRandomValues() {
        return new OrganizationBuilder();
    }

    public OrganizationBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public OrganizationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public OrganizationBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public OrganizationBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public OrganizationBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public OrganizationBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public OrganizationBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Organization build() {
        return new Organization(id, name, type, street, city, state, country);
    }


}
