package com.pablodev.organizationservice.organization.domain;

import java.util.UUID;

public class OrganizationIdMother {

    public static OrganizationId random() {
        return new OrganizationId(UUID.randomUUID().toString());
    }

}
