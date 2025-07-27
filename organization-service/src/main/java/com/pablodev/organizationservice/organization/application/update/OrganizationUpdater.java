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

    public void update(
            String id,
            String name,
            String type,
            String street,
            String city,
            String state,
            String country
    ) {

        ensureOrganizationExists(id);

        Organization organization = Organization.create(id, name, type, street, city, state,
                country);
        
        organizationRepository.save(organization);
    }

    private void ensureOrganizationExists(String id) {
        organizationRepository.findById(new OrganizationId(id))
                .orElseThrow(() -> new OrganizationDoesNotExistException(id));
    }

}
