package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class OrganizationAddressMother {

    public static OrganizationAddress random() {
        return new OrganizationAddress(
                Faker.instance().address().streetName(),
                Faker.instance().address().cityName(),
                Faker.instance().address().state(),
                Faker.instance().address().country()
        );
    }


}
