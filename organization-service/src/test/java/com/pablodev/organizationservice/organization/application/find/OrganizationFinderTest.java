package com.pablodev.organizationservice.organization.application.find;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationIdMother;
import com.pablodev.organizationservice.organization.domain.OrganizationMother;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationDoesNotExistException;
import com.pablodev.shared.domain.InvalidIdentifierException;
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
        OrganizationId id = OrganizationIdMother.create(organization.getId());

        OrganizationResponse expectedResponse = OrganizationResponse.fromAggregate(
                organization);

        when(repository.findById(id)).thenReturn(Optional.of(organization));

        OrganizationResponse response = finder.find(id.getValue());

        assertThat(response).isEqualTo(expectedResponse);

    }

    @Test
    void givenNotExistingId_whenFindById_thenThrowException() {

        OrganizationId id = OrganizationIdMother.random();

        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OrganizationDoesNotExistException.class, () -> finder.find(id.getValue()));
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowException() {
        assertThrows(InvalidIdentifierException.class, () -> finder.find("1"));
    }


}
