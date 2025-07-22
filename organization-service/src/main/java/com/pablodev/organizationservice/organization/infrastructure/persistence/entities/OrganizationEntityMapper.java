package com.pablodev.organizationservice.organization.infrastructure.persistence.entities;

import com.pablodev.organizationservice.organization.domain.*;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEntityMapper {
    public OrganizationEntity fromAggregate(Organization organization) {
        return new OrganizationEntity(
                organization.getId().getValue(),
                organization.getName().getValue(),
                organization.getAddress().getStreet(),
                organization.getAddress().getCity(),
                organization.getAddress().getState(),
                organization.getAddress().getCountry(),
                organization.getType().name()
        );
    }

    public Organization toAggregate(OrganizationEntity entity) {
        return new Organization(
                new OrganizationId(entity.getId()),
                new OrganizationName(entity.getName()),
                new OrganizationAddress(
                        entity.getStreet(),
                        entity.getCity(),
                        entity.getState(),
                        entity.getCountry()
                ),
                OrganizationType.valueOf(entity.getType())
        );
    }
}
