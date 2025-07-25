package com.pablodev.organizationservice.organization.infrastructure.persistence;

import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntity;
import com.pablodev.shared.infrastructure.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaOrganizationRepository extends BaseRepository<OrganizationEntity, String> {
    Optional<OrganizationEntity> findByName(String name);
}
