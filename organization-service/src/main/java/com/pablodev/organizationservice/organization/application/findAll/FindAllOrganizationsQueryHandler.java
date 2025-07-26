package com.pablodev.organizationservice.organization.application.findAll;

import com.pablodev.organizationservice.organization.application.OrganizationsResponse;
import com.pablodev.shared.domain.query.QueryHandler;

public class FindAllOrganizationsQueryHandler implements
        QueryHandler<FindAllOrganizationsQuery, OrganizationsResponse> {

    @Override
    public OrganizationsResponse handle(FindAllOrganizationsQuery query) {
        return null;
    }

}
