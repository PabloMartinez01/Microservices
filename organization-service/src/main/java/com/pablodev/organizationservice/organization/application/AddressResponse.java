package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.OrganizationAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressResponse {

    private String street;
    private String city;
    private String state;
    private String country;

    public static AddressResponse from(OrganizationAddress address) {
        return new AddressResponse(address.getStreet(), address.getCity(), address.getState(), address.getCountry());
    }
}
