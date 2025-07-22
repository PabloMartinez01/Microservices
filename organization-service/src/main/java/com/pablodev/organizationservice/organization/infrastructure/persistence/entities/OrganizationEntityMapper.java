package com.pablodev.organizationservice.organization.infrastructure.persistence.entities;

import com.pablodev.organizationservice.organization.domain.*;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEntityMapper {
    public OrganizationEntity fromAggregate(Organization organization) {
        return new OrganizationEntity(
                organization.getId().getValue(),
                organization.getName().getValue(),
                organization.getAddress().getStreet().getValue(),
                organization.getAddress().getCity().getValue(),
                organization.getAddress().getState().getValue(),
                organization.getAddress().getCountry().getValue(),
                organization.getType().name()
        );
    }

    public Organization toAggregate(OrganizationEntity entity) {
        return new Organization(
                new OrganizationId(entity.getId()),
                new OrganizationName(entity.getName()),
                new OrganizationAddress(
                        new AddressStreet(entity.getStreet()),
                        new AddressCity(entity.getCity()),
                        new AddressState(entity.getState()),
                        new AddressCountry(entity.getCountry())
                ),
                OrganizationType.valueOf(entity.getType())
        );
    }
}
