package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class AddressStreetMother {

    public static AddressStreet random() {
        return new AddressStreet(Faker.instance().address().streetName());
    }

}
