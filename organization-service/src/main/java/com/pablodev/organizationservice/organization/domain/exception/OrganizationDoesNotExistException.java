package com.pablodev.organizationservice.organization.domain.exception;

public class OrganizationDoesNotExistException extends RuntimeException {

    public OrganizationDoesNotExistException(String id) {
        super("Organization with id %s does not exist".formatted(id));
    }
}
