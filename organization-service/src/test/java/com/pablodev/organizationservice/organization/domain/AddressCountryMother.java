package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class AddressCountryMother {

    public static AddressCountry random() {
        return new AddressCountry(Faker.instance().address().country());
    }

}
