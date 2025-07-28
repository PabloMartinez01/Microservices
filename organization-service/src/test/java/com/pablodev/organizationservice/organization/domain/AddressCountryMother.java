package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class AddressCountryMother {
    
    public static AddressCountry random() {
        return new AddressCountry(Faker.instance().address().country());
    }

    public static AddressCountry create(String country) {
        return new AddressCountry(country);
    }

}
