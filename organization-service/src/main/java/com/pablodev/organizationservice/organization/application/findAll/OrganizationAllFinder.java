package com.pablodev.organizationservice.organization.application.findAll;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.application.OrganizationsResponse;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrganizationAllFinder {

    private final OrganizationRepository organizationRepository;

    public OrganizationsResponse findAll() {

        List<OrganizationResponse> organizationsResponse = organizationRepository.findAll().stream()
                .map(OrganizationResponse::fromAggregate)
                .toList();

        return new OrganizationsResponse(organizationsResponse);
    }

}
