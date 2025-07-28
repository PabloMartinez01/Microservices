package com.pablodev.organizationservice.organization.application.update;

public class UpdateOrganizationCommandMother {


    public static UpdateOrganizationCommand random() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .build();
    }

    public static UpdateOrganizationCommand withId(String id) {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withId(id)
                .build();
    }

    public static UpdateOrganizationCommand withInvalidId() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withId("invalid-id")
                .build();
    }

    public static UpdateOrganizationCommand withInvalidName() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withName(null)
                .build();
    }

    public static UpdateOrganizationCommand withInvalidType() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withType("invalid-type")
                .build();
    }

    public static UpdateOrganizationCommand withInvalidStreet() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withStreet(null)
                .build();
    }

    public static UpdateOrganizationCommand withInvalidCity() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withCity(null)
                .build();
    }

    public static UpdateOrganizationCommand withInvalidState() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withState(null)
                .build();
    }

    public static UpdateOrganizationCommand withInvalidCountry() {
        return UpdateOrganizationCommandBuilder.withRandomValues()
                .withCountry(null)
                .build();
    }
}
