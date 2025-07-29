package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class OrganizationTypeMother {

    public static OrganizationType random() {
        return create(Faker.instance().options().option("COMPANY", "INDIVIDUAL"));
    }

    public static OrganizationType create(String type) {
        return OrganizationType.from(type);
    }

}
