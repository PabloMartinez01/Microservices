package com.pablodev.organizationservice.organization.application;

import com.pablodev.shared.domain.query.QueryResponse;
import java.util.List;

public record OrganizationsResponse(List<OrganizationResponse> organizations)
        implements QueryResponse {

}
