package com.pablodev.organizationservice.organization.application.create;

public record CreateOrganizationRequest(String id, String name, String type, OrganizationAddressRequest address) {
    public record OrganizationAddressRequest(String street, String city, String state, String country) {}
}