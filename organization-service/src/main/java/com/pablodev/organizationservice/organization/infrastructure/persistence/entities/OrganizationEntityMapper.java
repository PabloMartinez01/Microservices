package com.pablodev.organizationservice.organization.infrastructure.persistence.entities;

import com.pablodev.organizationservice.organization.domain.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEntityMapper {

    public OrganizationEntity fromAggregate(Organization organization) {
        return new OrganizationEntity(
                organization.getId(),
                organization.getName(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getCountry(),
                organization.getType()
        );
    }

    public Organization toAggregate(OrganizationEntity entity) {
        return new Organization(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getStreet(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry()
        );
    }
}
