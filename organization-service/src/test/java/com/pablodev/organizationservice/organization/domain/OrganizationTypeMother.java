package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class OrganizationTypeMother {

    public static OrganizationType random() {
        return OrganizationType.from(Faker.instance().options().option("COMPANY", "INDIVIDUAL"));
    }

}
