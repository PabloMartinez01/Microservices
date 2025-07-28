package com.pablodev.organizationservice.organization.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OrganizationAddress {

    private final AddressStreet street;
    private final AddressCity city;
    private final AddressState state;
    private final AddressCountry country;

    public OrganizationAddress(String street, String city, String state, String country) {
        this.street = new AddressStreet(street);
        this.city = new AddressCity(city);
        this.state = new AddressState(state);
        this.country = new AddressCountry(country);
    }

    public String getStreet() {
        return street.getValue();
    }

    public String getCity() {
        return city.getValue();
    }

    public String getState() {
        return state.getValue();
    }

    public String getCountry() {
        return country.getValue();
    }

}
