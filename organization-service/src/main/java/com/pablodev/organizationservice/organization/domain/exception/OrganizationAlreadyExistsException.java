package com.pablodev.organizationservice.organization.domain.exception;

public class OrganizationAlreadyExistsException extends RuntimeException {

    public OrganizationAlreadyExistsException(String name) {
        super("Organization with name %s already exists".formatted(name));
    }
}
