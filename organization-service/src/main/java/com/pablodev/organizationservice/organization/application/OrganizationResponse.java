package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.shared.domain.query.QueryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class OrganizationResponse implements QueryResponse {

    private final String id;
    private final String name;
    private final String type;
    private final OrganizationAddressData address;


    public static OrganizationResponse fromAggregate(Organization organization) {
        return new OrganizationResponse(
                organization.getId(),
                organization.getName(),
                organization.getType(),
                OrganizationAddressData.from(organization)
        );
    }


}

