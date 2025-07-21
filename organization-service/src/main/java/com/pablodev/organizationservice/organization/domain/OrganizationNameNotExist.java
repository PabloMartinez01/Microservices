package com.pablodev.organizationservice.organization.domain;

public class OrganizationNameNotExist extends RuntimeException {
  public OrganizationNameNotExist(OrganizationName name) {
    super(String.format("Organization with name %s not found", name.getValue()));
  }
}
