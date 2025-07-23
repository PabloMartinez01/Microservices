package com.pablodev.organizationservice.organization.application.find;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationIdDoesNotExist;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationFinder {

    private final OrganizationRepository organizationRepository;

    public OrganizationResponse find(FindOrganizationQuery findOrganizationQuery) {
        OrganizationId id = new OrganizationId(findOrganizationQuery.id());
        return organizationRepository.findById(id)
                .map(OrganizationResponse::fromAggregate)
                .orElseThrow(() -> new OrganizationIdDoesNotExist(id.getValue()));

    }


}
