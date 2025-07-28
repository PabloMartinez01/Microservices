package com.pablodev.organizationservice.organization.application.create;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void should_create_organization() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.random();

        organizationCreator.create(command);

        verify(organizationRepository, times(1)).save(any());
    }

    @Test
    void should_throw_exception_when_id_invalid() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidId();

        assertThrows(IllegalArgumentException.class,
                () -> organizationCreator.create(command));

    }

    @Test
    void should_throw_exception_when_name_invalid() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidName();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void should_throw_exception_when_type_invalid() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidType();

        assertThrows(UnknownOrganizationTypeException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void should_throw_exception_when_street_invalid() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidStreet();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void should_throw_exception_when_state_invalid() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidState();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }

    @Test
    void should_throw_exception_when_country_invalid() {

        CreateOrganizationCommand command = CreateOrganizationCommandMother.withInvalidCountry();

        assertThrows(OrganizationIllegalArgumentException.class,
                () -> organizationCreator.create(command));
    }


}
