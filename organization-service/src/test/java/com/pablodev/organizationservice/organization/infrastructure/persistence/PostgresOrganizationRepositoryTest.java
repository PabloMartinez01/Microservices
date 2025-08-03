package com.pablodev.organizationservice.organization.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.organization.domain.OrganizationMother;
import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.Order;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostgresOrganizationRepositoryTest {

    @Autowired
    private PostgresOrganizationRepository repository;

    @Test
    void givenOrganization_whenSave_shouldSaveOrganization() {

        Organization organization = OrganizationMother.random();

        repository.save(organization);

        Optional<Organization> response = repository.findById(
                new OrganizationId(organization.getId()));

        assertThat(response).isPresent().hasValue(organization);
    }

    @Test
    void givenMultiplePersistedOrganizations_whenFindAll_thenReturnsAllOrganizations() {

        List<Organization> expectedOrganizations = List.of(
                OrganizationMother.random(),
                OrganizationMother.random(),
                OrganizationMother.random());

        expectedOrganizations.forEach(repository::save);

        List<Organization> organizations = repository.findAll();

        assertThat(organizations).isEqualTo(expectedOrganizations);

    }

    @Test
    void givenOrganizations_whenFilteringByNameAndOrdering_thenReturnsFilteredAndOrderedOrganizations() {
        Organization o1 = OrganizationMother.withName("Corporation1");
        Organization o2 = OrganizationMother.withName("Corporation2");
        Organization o3 = OrganizationMother.withName("Other enterprise");

        List.of(o1, o2, o3).forEach(repository::save);

        List<Organization> result = repository.search(Criteria.of(
                Order.ascending("name"),
                Filter.contains("name", "Corporation")
        ));

        assertThat(result).isEqualTo(List.of(o1, o2));
    }

    @Test
    void givenOrganizations_whenFilteringByCountry_thenReturnsFilteredOrganizations() {

        Organization o1 = OrganizationMother.withCountry("Spain");
        Organization o2 = OrganizationMother.withCountry("Spain");
        Organization o3 = OrganizationMother.withCountry("United Kingdom");

        List.of(o1, o2, o3).forEach(repository::save);

        List<Organization> organizations = repository.search(Criteria.of(
                Order.unordered(),
                Filter.equals("country", "Spain")
        ));

        assertThat(organizations).containsExactlyInAnyOrder(o1, o2);

    }


}
