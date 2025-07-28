package com.pablodev.organizationservice.organization.application.find_all;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.application.OrganizationsResponse;
import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationMother;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrganizationFinderAllTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationFinderAll organizationFinderAll;

    @Test
    void givenNothing_whenFindAll_thenReturnAllOrganizations() {

        List<Organization> organizations = createOrganizations();
        OrganizationsResponse expectedResponse = createExpectedResponse(organizations);

        when(organizationRepository.findAll()).thenReturn(organizations);

        OrganizationsResponse organizationsResponse = organizationFinderAll.findAll();

        assertThat(expectedResponse).isEqualTo(organizationsResponse);

    }

    private List<Organization> createOrganizations() {
        return List.of(
                OrganizationMother.random(),
                OrganizationMother.random(),
                OrganizationMother.random()
        );
    }

    private OrganizationsResponse createExpectedResponse(List<Organization> organizations) {
        return new OrganizationsResponse(
                organizations.stream()
                        .map(OrganizationResponse::fromAggregate).toList()
        );
    }


}
