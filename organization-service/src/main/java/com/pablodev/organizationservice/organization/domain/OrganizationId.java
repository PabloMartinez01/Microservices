package com.pablodev.organizationservice.organization.domain;

import com.pablodev.shared.domain.Identifier;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class OrganizationId extends Identifier {

    public OrganizationId(String value) {
        super(value);
    }
}
