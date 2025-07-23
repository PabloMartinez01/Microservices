package com.pablodev.organizationservice.organization.application.create;

public record CreateOrganizationCommand(
        String id,
        String name,
        String type,
        String street,
        String city,
        String state,
        String country
) {}