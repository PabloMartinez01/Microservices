package com.pablodev.organizationservice.organization.application.find;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationMother;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrganizationFinderTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationFinder organizationFinder;

    @Test
    void givenValidId_whenFindById_thenReturnOrganizationResponse() {

        Organization organization = OrganizationMother.random();
        OrganizationResponse expectedResponse = OrganizationResponse.fromAggregate(
                organization);

        when(organizationRepository.findById(new OrganizationId(organization.getId())))
                .thenReturn(Optional.of(organization));

        OrganizationResponse response = organizationFinder.find(organization.getId());

        assertThat(response).isEqualTo(expectedResponse);

    }


}
