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
    private OrganizationRepository repository;

    @InjectMocks
    private OrganizationFinder finder;

    @Test
    void givenValidId_whenFindById_thenReturnOrganizationResponse() {

        Organization organization = OrganizationMother.random();
        OrganizationId id = new OrganizationId(organization.getId());

        OrganizationResponse expectedResponse = OrganizationResponse.fromAggregate(
                organization);

        when(repository.findById(id)).thenReturn(Optional.of(organization));

        OrganizationResponse response = finder.find(id.getValue());

        assertThat(response).isEqualTo(expectedResponse);

    }


}
