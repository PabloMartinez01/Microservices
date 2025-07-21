package com.pablodev.organizationservice.organization.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrganizationsResponse {
    List<OrganizationResponse> organizations;
}
