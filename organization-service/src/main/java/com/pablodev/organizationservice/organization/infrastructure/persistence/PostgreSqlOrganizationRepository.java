package com.pablodev.organizationservice.organization.infrastructure.persistence;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntity;
import com.pablodev.organizationservice.organization.infrastructure.persistence.entities.OrganizationEntityMapper;
import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.infrastructure.criteria.CriteriaConverter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class PostgreSqlOrganizationRepository implements OrganizationRepository {

    private final JpaOrganizationRepository organizationRepository;
    private final OrganizationEntityMapper organizationMapper;
    private final CriteriaConverter<OrganizationEntity> criteriaConverter;

    public PostgreSqlOrganizationRepository(JpaOrganizationRepository organizationRepository,
            OrganizationEntityMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.criteriaConverter = new CriteriaConverter<>();
    }

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
    public List<Organization> search(Criteria criteria) {
        Specification<OrganizationEntity> specification = criteriaConverter.toSpecification(
                criteria);
        Sort sort = criteriaConverter.toSort(criteria);
        return organizationRepository.findAll(specification, sort).stream()
                .map(organizationMapper::toAggregate)
                .toList();
    }


}
