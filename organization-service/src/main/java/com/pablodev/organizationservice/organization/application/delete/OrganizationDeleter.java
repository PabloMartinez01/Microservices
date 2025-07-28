package com.pablodev.organizationservice.organization.application.delete;

import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationDeleter {

    private final OrganizationRepository organizationRepository;

    public void delete(String id) {

        ensureOrganizationExists(id);

        organizationRepository.deleteById(new OrganizationId(id));
    }

    private void ensureOrganizationExists(String id) {
        organizationRepository.findById(new OrganizationId(id))
                .orElseThrow(() -> new OrganizationDoesNotExistException(id));
    }

}
