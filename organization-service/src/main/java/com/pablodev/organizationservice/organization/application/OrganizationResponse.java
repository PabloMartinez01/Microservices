package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.shared.domain.query.QueryResponse;

public record OrganizationResponse(
        String id,
        String name,
        String type,
        OrganizationAddressResponse address) implements QueryResponse {

    public static OrganizationResponse fromAggregate(Organization organization) {
        return new OrganizationResponse(
                organization.getId(),
                organization.getName(),
                organization.getType(),
                OrganizationAddressResponse.from(organization)
        );
    }

}

