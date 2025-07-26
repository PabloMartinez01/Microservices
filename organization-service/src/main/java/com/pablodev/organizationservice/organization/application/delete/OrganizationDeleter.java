package com.pablodev.organizationservice.organization.application.delete;

import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationDeleter {

    private final OrganizationRepository organizationRepository;

    public void delete(String id) {
        organizationRepository.deleteById(new OrganizationId(id));
    }

}
