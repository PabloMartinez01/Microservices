package com.pablodev.organizationservice.organization.domain;

import com.pablodev.shared.domain.AggregateRoot;


public class Organization extends AggregateRoot {

    private final OrganizationId id;
    private final OrganizationName name;
    private final OrganizationType type;
    private final OrganizationAddress address;

    public Organization(
            String id,
            String name,
            String type,
            String street,
            String city,
            String state,
            String country
    ) {
        this.id = new OrganizationId(id);
        this.name = new OrganizationName(name);
        this.address = new OrganizationAddress(street, city, state, country);
        this.type = OrganizationType.from(type);
    }

    public static Organization create(
            String id,
            String name,
            String type,
            String street,
            String city,
            String state,
            String country
    ) {
        return new Organization(id, name, type, street, city, state, country);
    }

    public String getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getType() {
        return type.name();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getState() {
        return address.getState();
    }

    public String getCountry() {
        return address.getCountry();
    }


}

