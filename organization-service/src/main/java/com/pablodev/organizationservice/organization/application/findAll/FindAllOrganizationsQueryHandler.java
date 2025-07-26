package com.pablodev.organizationservice.organization.application.findAll;

import com.pablodev.organizationservice.organization.application.OrganizationsResponse;
import com.pablodev.shared.domain.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllOrganizationsQueryHandler implements
        QueryHandler<FindAllOrganizationsQuery, OrganizationsResponse> {

    private final OrganizationAllFinder organizationAllFinder;

    @Override
    public OrganizationsResponse handle(FindAllOrganizationsQuery query) {
        return organizationAllFinder.findAll();
    }

}
