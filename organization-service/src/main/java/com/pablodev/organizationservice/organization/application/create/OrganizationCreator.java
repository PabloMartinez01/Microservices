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

    public void create(OrganizationId id, OrganizationName name, OrganizationAddress address, OrganizationType type) {
        Organization organization = new Organization(id, name, address, type);
        repository.create(organization);
    }

}
