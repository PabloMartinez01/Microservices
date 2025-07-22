package com.pablodev.organizationservice.organization.domain;

import com.pablodev.shared.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Organization extends Aggregate {
    private OrganizationId id;
    private OrganizationName name;
    private OrganizationAddress address;
    private OrganizationType type;

    public static Organization create(OrganizationId id, OrganizationName name, OrganizationAddress address, OrganizationType type) {
        return new Organization(id, name, address, type);
    }


}
