package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class AddressCityMother {

    public static AddressCity random() {
        return new AddressCity(Faker.instance().address().cityName());
    }

}
