package com.pablodev.organizationservice.organization.application.find_all;

import com.pablodev.organizationservice.organization.application.OrganizationsResponse;
import com.pablodev.shared.domain.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllOrganizationsQueryHandler implements
        QueryHandler<FindAllOrganizationsQuery, OrganizationsResponse> {

    private final OrganizationFinderAll organizationFinderAll;

    @Override
    public OrganizationsResponse handle(FindAllOrganizationsQuery query) {
        return organizationFinderAll.findAll();
    }

}
