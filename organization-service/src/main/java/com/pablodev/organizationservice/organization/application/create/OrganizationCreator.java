package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.organizationservice.organization.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationCreator {

    private final OrganizationRepository repository;

    public void create(CreateOrganizationRequest request) {

        Organization organization = Organization.create(
                request.id(),
                request.name(),
                OrganizationAddress.create(
                        request.address().street(),
                        request.address().city(),
                        request.address().state(),
                        request.address().country()
                ),
                request.type()
        );

        repository.save(organization);
    }

}
