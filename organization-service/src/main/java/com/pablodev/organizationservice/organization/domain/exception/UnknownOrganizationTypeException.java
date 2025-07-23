package com.pablodev.organizationservice.organization.domain.exception;

public class UnknownOrganizationTypeException extends RuntimeException {
    public UnknownOrganizationTypeException(String type) {
        super("Unknown organization type: %s".formatted(type));
    }
}
