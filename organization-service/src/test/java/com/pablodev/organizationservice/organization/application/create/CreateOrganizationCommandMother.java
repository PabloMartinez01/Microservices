package com.pablodev.organizationservice.organization.application.create;

public class CreateOrganizationCommandMother {


    public static CreateOrganizationCommand random() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .build();
    }

    public static CreateOrganizationCommand withInvalidId() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .withId("invalid-id")
                .build();
    }

    public static CreateOrganizationCommand withInvalidName() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .withName(null)
                .build();
    }

    public static CreateOrganizationCommand withInvalidType() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .withType("invalid-type")
                .build();
    }

    public static CreateOrganizationCommand withInvalidStreet() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .withStreet(null)
                .build();
    }

    public static CreateOrganizationCommand withInvalidCity() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .withCity(null)
                .build();
    }

    public static CreateOrganizationCommand withInvalidState() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .withState(null)
                .build();
    }

    public static CreateOrganizationCommand withInvalidCountry() {
        return CreateOrganizationCommandBuilder.withRandomValues()
                .withCountry(null)
                .build();
    }

}
