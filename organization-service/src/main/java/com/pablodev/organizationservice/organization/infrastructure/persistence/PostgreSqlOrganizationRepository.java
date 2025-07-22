package com.pablodev.organizationservice.organization.infrastructure.persistence;

import com.pablodev.organizationservice.organization.domain.*;
import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntity;
import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgreSqlOrganizationRepository implements OrganizationRepository {

    private final JpaOrganizationRepository organizationRepository;
    private final OrganizationEntityMapper organizationMapper;

    @Override
    public void save(Organization organization) {
        OrganizationEntity organizationEntity = organizationMapper.fromAggregate(organization);
        organizationRepository.save(organizationEntity);
    }

    @Override
    public Optional<Organization> findById(OrganizationId id) {
        return organizationRepository.findById(id.getValue())
                .map(organizationMapper::toAggregate);
    }

    @Override
    public Optional<Organization> findByName(OrganizationName name) {
        return organizationRepository.findByName(name.getValue())
                .map(organizationMapper::toAggregate);
    }

    @Override
    public List<Organization> findAll() {
       return organizationRepository.findAll().stream()
                .map(organizationMapper::toAggregate)
                .toList();
    }


    @Override
    public void deleteById(OrganizationId id) {
        organizationRepository.deleteById(id.getValue());
    }

    @Override
    public void deleteByName(OrganizationName name) {
        organizationRepository.deleteByName(name.getValue());
    }
}
