package com.pablodev.organizationservice.organization.domain.exception;

import com.pablodev.organizationservice.organization.domain.OrganizationName;

public class OrganizationNameNotExist extends RuntimeException {
  public OrganizationNameNotExist(OrganizationName name) {
    super(String.format("Organization with name %s not found", name.getValue()));
  }
}
