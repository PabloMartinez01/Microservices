package com.pablodev.organizationservice.organization.application.delete;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
class OrganizationDeleterTest {

    @Mock
    private OrganizationRepository repository;

    @InjectMocks
    private OrganizationDeleter deleter;

    @Test
    void givenExistentId_whenDeleteOrganization_thenDeleteOrganization() {

        Organization existentOrganization = OrganizationMother.random();
        OrganizationId id = OrganizationIdMother.create(existentOrganization.getId());

        when(repository.findById(id)).thenReturn(Optional.of(existentOrganization));

        deleter.delete(existentOrganization.getId());

        verify(repository, times(1)).deleteById(id);

    }

    @Test
    void givenNotExistentId_whenDeleteOrganization_thenThrowException() {

        String id = OrganizationIdMother.random().getValue();
        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OrganizationDoesNotExistException.class, () -> deleter.delete(id));
    }

    @Test
    void givenInvalidId_whenDeleteOrganization_thenThrowException() {
        assertThrows(InvalidIdentifierException.class, () -> deleter.delete("1"));
    }


}
