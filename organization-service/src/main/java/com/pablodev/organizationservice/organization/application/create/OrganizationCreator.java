package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.organizationservice.organization.domain.*;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationAlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationCreator {

    private final OrganizationRepository repository;

    public void create(CreateOrganizationCommand command) {

        Organization organization = createOrganization(command);

        ensureOrganizationNameNotExist(command.name());

        repository.save(organization);
    }

    private void ensureOrganizationNameNotExist(String name) {
        repository.findByName(new OrganizationName(name)).ifPresent(organization -> {
            throw new OrganizationAlreadyExistsException(organization.getName());
        });
    }

    private Organization createOrganization(CreateOrganizationCommand command) {
        return Organization.create(
                command.id(),
                command.name(),
                command.type(),
                command.street(),
                command.city(),
                command.state(),
                command.country()
        );
    }
}
