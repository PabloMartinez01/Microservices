package com.pablodev.organizationservice.organization.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrganizationAddress {
    private String street;
    private String city;
    private String state;
    private String country;
}
