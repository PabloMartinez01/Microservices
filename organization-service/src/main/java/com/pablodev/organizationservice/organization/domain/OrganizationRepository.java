package com.pablodev.organizationservice.organization.domain;

import java.util.List;

public interface OrganizationRepository {
    void createOrganization(Organization organization);
    Organization findOrganizationById(OrganizationId id);
    Organization findOrganizationByName(OrganizationName name);
    List<Organization> findAllOrganizations();
    void updateOrganization(OrganizationId id, Organization organization);
    void deleteOrganizationById(OrganizationId id);
    void deleteOrganizationByName(OrganizationName name);
}
