package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.Organization;

public record OrganizationResponse(
        String id,
        String name,
        String type,
        OrganizationAddressResponse address
) {

    public static OrganizationResponse fromAggregate(Organization organization) {
        return new OrganizationResponse(
                organization.getId(),
                organization.getName(),
                organization.getType(),
                OrganizationAddressResponse.from(organization)
        );
    }
}

