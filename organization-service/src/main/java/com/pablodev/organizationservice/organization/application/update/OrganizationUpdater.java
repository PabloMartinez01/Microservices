package com.pablodev.organizationservice.organization.application.update;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationUpdater {

    private final OrganizationRepository organizationRepository;

    public void update(UpdateOrganizationCommand command) {

        ensureOrganizationExists(command.getId());

        Organization organization = createOrganization(command);

        organizationRepository.save(organization);
    }

    private Organization createOrganization(UpdateOrganizationCommand command) {
        return Organization.create(
                command.getId(),
                command.getName(),
                command.getType(),
                command.getAddress().getStreet(),
                command.getAddress().getCity(),
                command.getAddress().getState(),
                command.getAddress().getCountry()
        );
    }

    private void ensureOrganizationExists(String id) {
        organizationRepository.findById(new OrganizationId(id))
                .orElseThrow(() -> new OrganizationDoesNotExistException(id));
    }

}
