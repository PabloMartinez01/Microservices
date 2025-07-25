package com.pablodev.organizationservice.organization.application.find;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.shared.domain.query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOrganizationQueryHandler implements QueryHandler<FindOrganizationQuery, OrganizationResponse> {

    private final OrganizationFinder organizationFinder;

    @Override
    public OrganizationResponse handle(FindOrganizationQuery query) {
        return organizationFinder.find(query.id());
    }
}
