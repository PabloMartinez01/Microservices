package com.pablodev.organizationservice.organization.domain.exception;

public class OrganizationIdDoesNotExist extends RuntimeException {
  public OrganizationIdDoesNotExist(String id) {
    super("Organization with id %s does not exist".formatted(id));
  }
}
