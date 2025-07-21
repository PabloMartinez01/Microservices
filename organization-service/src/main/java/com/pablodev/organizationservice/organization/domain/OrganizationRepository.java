package com.pablodev.organizationservice.organization.domain;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {
    void createOrganization(Organization organization);
    Optional<Organization> findOrganizationById(OrganizationId id);
    Optional<Organization> findOrganizationByName(OrganizationName name);
    List<Organization> findAllOrganizations();
    void updateOrganization(OrganizationId id, Organization organization);
    void deleteOrganizationById(OrganizationId id);
    void deleteOrganizationByName(OrganizationName name);
}
