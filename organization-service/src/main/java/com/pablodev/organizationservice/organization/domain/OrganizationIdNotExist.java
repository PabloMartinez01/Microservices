package com.pablodev.organizationservice.organization.domain;

public class OrganizationIdNotExist extends RuntimeException {
  public OrganizationIdNotExist(OrganizationId id) {
    super(String.format("Organization with id %s not found", id.getValue()));
  }
}
