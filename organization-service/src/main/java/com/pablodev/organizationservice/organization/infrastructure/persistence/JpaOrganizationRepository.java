package com.pablodev.organizationservice.organization.infrastructure.persistence;

import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntity;
import com.pablodev.shared.infrastructure.repository.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrganizationRepository extends BaseRepository<OrganizationEntity, String> {

    Optional<OrganizationEntity> findByName(String name);
}
