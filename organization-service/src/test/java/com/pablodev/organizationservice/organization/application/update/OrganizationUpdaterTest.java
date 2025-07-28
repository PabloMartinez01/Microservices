package com.pablodev.organizationservice.organization.application.update;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationMother;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationDoesNotExistException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrganizationUpdaterTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationUpdater organizationUpdater;

    @Test
    void givenExistentOrganization_whenUpdateOrganization_thenShouldUpdateOrganization() {

        Organization existentOrganization = OrganizationMother.random();
        UpdateOrganizationCommand command = UpdateOrganizationCommandMother
                .withId(existentOrganization.getId());

        Organization expectedOrganization = createExpectedOrganization(command);

        when(organizationRepository.findById(new OrganizationId(command.id())))
                .thenReturn(Optional.of(existentOrganization));

        organizationUpdater.update(command);

        verify(organizationRepository, times(1)).save(expectedOrganization);

    }

    @Test
    void givenNotExistentOrganization_whenUpdateOrganization_thenShouldThrowException() {

        UpdateOrganizationCommand command = UpdateOrganizationCommandMother.random();

        when(organizationRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OrganizationDoesNotExistException.class,
                () -> organizationUpdater.update(command));

    }

    private Organization createExpectedOrganization(UpdateOrganizationCommand command) {
        return OrganizationMother.create(
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
