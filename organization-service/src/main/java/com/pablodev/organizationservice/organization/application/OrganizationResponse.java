package com.pablodev.organizationservice.organization.application;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrganizationResponse {

    private String id;
    private String name;
    private String type;
    private AddressResponse address;

    public static OrganizationResponse fromAggregate(Organization organization) {
        return new OrganizationResponse(
                organization.getId().getValue(),
                organization.getName().getValue(),
                organization.getType().name(),
                AddressResponse.from(organization.getAddress())
        );
    }

}
