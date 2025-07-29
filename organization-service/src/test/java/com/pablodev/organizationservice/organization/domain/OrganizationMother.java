package com.pablodev.organizationservice.organization.domain;

public class OrganizationMother {

    public static Organization create(
            String id,
            String name,
            String type,
            String street,
            String city,
            String state,
            String country) {
        return new Organization(id, name, type, street, city, state, country);
    }

    public static Organization random() {
        return OrganizationBuilder.withRandomValues().build();
    }

    public static Organization withName(String name) {
        return OrganizationBuilder.withRandomValues()
                .withName(name)
                .build();
    }

    public static Organization withCountry(String country) {
        return OrganizationBuilder.withRandomValues()
                .withCountry(country)
                .build();
    }


}
