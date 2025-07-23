package com.pablodev.organizationservice.organization.domain.exception;

public class OrganizationAlreadyExists extends RuntimeException {
    public OrganizationAlreadyExists(String name) {
        super("Organization with name %s already exists".formatted(name));
    }
}
