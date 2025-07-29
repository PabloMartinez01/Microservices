package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class AddressCityMother {

    public static AddressCity random() {
        return create(Faker.instance().address().cityName());
    }

    public static AddressCity create(String city) {
        return new AddressCity(city);
    }
}
