package com.pablodev.organizationservice.organization.domain;

import java.util.List;

public interface OrganizationRepository {
    void create(Organization organization);
    Organization findById(OrganizationId id);
    Organization findByName(OrganizationName name);
    List<Organization> findAll();
    void update(OrganizationId id, Organization organization);
    void deleteById(OrganizationId id);
    void deleteByName(OrganizationName name);
}
