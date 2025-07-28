package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class OrganizationNameMother {

    public static OrganizationName random() {
        return new OrganizationName(Faker.instance().company().name());
    }

    public static OrganizationName create(String name) {
        return new OrganizationName(name);
    }

}
