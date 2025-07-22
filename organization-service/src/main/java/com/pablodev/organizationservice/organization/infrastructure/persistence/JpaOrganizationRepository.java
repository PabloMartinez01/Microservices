package com.pablodev.organizationservice.organization.infrastructure.persistence;

import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaOrganizationRepository extends JpaRepository<OrganizationEntity, String> {
    Optional<OrganizationEntity> findByName(String name);
    void deleteByName(String name);
}
