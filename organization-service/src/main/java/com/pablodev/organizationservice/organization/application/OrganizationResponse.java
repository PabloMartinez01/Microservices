package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrganizationResponse {

    private String id;
    private String name;
    private String type;
    private OrganizationAddressResponse address;

    @Getter
    @AllArgsConstructor
    private static class OrganizationAddressResponse {
        private String street;
        private String city;
        private String state;
        private String country;
    }

}
