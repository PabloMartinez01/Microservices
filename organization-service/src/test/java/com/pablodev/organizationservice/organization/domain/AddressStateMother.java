package com.pablodev.organizationservice.organization.domain;

import com.github.javafaker.Faker;

public class AddressStateMother {

    public static AddressState random() {
        return create(Faker.instance().address().state());
    }

    public static AddressState create(String state) {
        return new AddressState(state);
    }

}

