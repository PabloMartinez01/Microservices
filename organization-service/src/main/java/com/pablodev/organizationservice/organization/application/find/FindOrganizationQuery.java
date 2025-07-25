package com.pablodev.organizationservice.organization.application.find;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.shared.domain.query.Query;

public record FindOrganizationQuery(String id) implements Query<OrganizationResponse> {}
