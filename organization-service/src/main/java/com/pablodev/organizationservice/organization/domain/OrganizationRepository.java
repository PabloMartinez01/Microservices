package com.pablodev.organizationservice.organization.domain;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {
    void save(Organization organization);
    Optional<Organization> findById(OrganizationId id);
    Optional<Organization> findByName(OrganizationName name);
    List<Organization> findAll();
    void deleteById(OrganizationId id);
    void deleteByName(OrganizationName name);
}
