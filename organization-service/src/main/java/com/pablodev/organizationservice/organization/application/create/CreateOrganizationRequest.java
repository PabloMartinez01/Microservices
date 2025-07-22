package com.pablodev.organizationservice.organization.application.create;

public record CreateOrganizationRequest(
        String id,
        String name,
        String type,
        CreateOrganizationAddressRequest address
) {
    public record CreateOrganizationAddressRequest(
            String street,
            String city,
            String state,
            String country
    ) {}
}