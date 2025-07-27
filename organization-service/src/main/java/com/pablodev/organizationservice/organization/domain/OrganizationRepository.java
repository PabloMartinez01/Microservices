package com.pablodev.organizationservice.organization.domain;

import com.pablodev.shared.domain.criteria.Criteria;
import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {

    void save(Organization organization);

    Optional<Organization> findById(OrganizationId id);

    List<Organization> findAll();

    List<Organization> search(Criteria criteria);

    void deleteById(OrganizationId id);
}
