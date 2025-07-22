package com.pablodev.organizationservice.organization.domain;

import com.pablodev.shared.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Organization extends Aggregate {

    private OrganizationId id;
    private OrganizationName name;
    private OrganizationAddress address;
    private OrganizationType type;

    public static Organization create(OrganizationId id, OrganizationName name, OrganizationAddress address, OrganizationType type) {
        return new Organization(id, name, address, type);
    }

    public static Organization create(String id, String name, OrganizationAddress address, String type) {
        return new Organization(
                new OrganizationId(id),
                new OrganizationName(name),
                address,
                OrganizationType.valueOf(type)
        );
    }


}
