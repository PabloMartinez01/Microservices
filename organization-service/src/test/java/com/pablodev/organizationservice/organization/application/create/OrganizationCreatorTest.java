package com.pablodev.organizationservice.organization.application.create;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationMother;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import com.pablodev.organizationservice.organization.domain.exception.UnknownOrganizationTypeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrganizationCreatorTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationCreator organizationCreator;

    @Test
    void givenValidCreateOrganizationCommand_whenCreateOrganization_thenCreateOrganization() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.random();
        Organization expectedOrganization = createExpectedOrganization(command);

        organizationCreator.create(command);

        verify(organizationRepository, times(1)).save(expectedOrganization);
    }

    @Test
    void givenInvalidId_whenCreateOrganization_thenThrowException() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidId();

        assertThrows(IllegalArgumentException.class,
                () -> organizationCreator.create(command));

    }

    @Test
    void givenInvalidName_whenCreateOrganization_thenThrowException() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidName();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void givenInvalidType_whenCreateOrganization_thenThrowException() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidType();

        assertThrows(UnknownOrganizationTypeException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void givenInvalidStreet_whenCreateOrganization_thenThrowException() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidStreet();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void givenInvalidCity_whenCreateOrganization_thenThrowException() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidCity();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }


    @Test
    void givenInvalidState_whenCreateOrganization_thenThrowException() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidState();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void givenInvalidCountry_whenCreateOrganization_thenThrowException() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidCountry();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }

    private Organization createExpectedOrganization(CreateOrganizationCommand command) {
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
