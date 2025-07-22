package com.pablodev.organizationservice.organization.infrastructure.persistence;

import com.pablodev.organizationservice.organization.domain.*;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationIdNotExist;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationNameNotExist;
import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntity;
import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class PostgreSqlOrganizationRepository implements OrganizationRepository {

    private final JpaOrganizationRepository jpaOrganizationRepository;
    private final OrganizationEntityMapper organizationEntityMapper;

    @Override
    public void create(Organization organization) {
        OrganizationEntity organizationEntity = organizationEntityMapper.fromAggregate(organization);
        jpaOrganizationRepository.save(organizationEntity);
    }

    @Override
    public Organization findById(OrganizationId id) {
        return jpaOrganizationRepository.findById(id.getValue())
                .map(organizationEntityMapper::toAggregate)
                .orElseThrow(() -> new OrganizationIdNotExist(id));
    }

    @Override
    public Organization findByName(OrganizationName name) {
        return jpaOrganizationRepository.findByName(name.getValue())
                .map(organizationEntityMapper::toAggregate)
                .orElseThrow(() -> new OrganizationNameNotExist(name));
    }

    @Override
    public List<Organization> findAll() {
       return StreamSupport.stream(jpaOrganizationRepository.findAll().spliterator(), false)
                .map(organizationEntityMapper::toAggregate)
                .toList();
    }

    @Override
    public void update(OrganizationId id, Organization organization) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(OrganizationId id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteByName(OrganizationName name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
