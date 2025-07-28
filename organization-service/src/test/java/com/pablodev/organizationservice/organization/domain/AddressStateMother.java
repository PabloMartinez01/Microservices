package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class AddressStateMother {

    public static AddressState random() {
        return new AddressState(Faker.instance().address().state());
    }

}
