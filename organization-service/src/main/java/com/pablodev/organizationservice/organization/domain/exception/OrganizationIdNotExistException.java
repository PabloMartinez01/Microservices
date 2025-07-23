package com.pablodev.organizationservice.organization.domain.exception;

public class OrganizationIdNotExistException extends RuntimeException {
  public OrganizationIdNotExistException(String id) {
    super("Organization with id %s does not exist".formatted(id));
  }
}
