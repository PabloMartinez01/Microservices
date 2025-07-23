package com.pablodev.organizationservice.organization.application.find;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationDoesNotExistException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationFinder {

    private final OrganizationRepository repository;

    public OrganizationResponse find(FindOrganizationQuery query) {
        OrganizationId id = new OrganizationId(query.id());
        return repository.findById(id)
                .map(OrganizationResponse::fromAggregate)
                .orElseThrow(() -> new OrganizationDoesNotExistException(id.getValue()));

    }


}
