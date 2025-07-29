package com.pablodev.organizationservice.organization.infrastructure.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationMother;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/db/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class OrganizationGetControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrganizationRepository repository;


    @Test
    void givenValidId_whenGetOrganization_thenShouldReturnOrganization() {

        Organization organization = OrganizationMother.random();
        repository.save(organization);

        ResponseEntity<OrganizationResponse> response = restTemplate.getForEntity(
                "/organization/" + organization.getId(),
                OrganizationResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(OrganizationResponse.fromAggregate(organization));
    }

    @Test
    void givenIdOfNotExistingOrganization_whenGetOrganization_thenShouldReturnNotFound() {
        ResponseEntity<OrganizationResponse> forEntity = restTemplate.getForEntity(
                "/organization/" + UUID.randomUUID(), OrganizationResponse.class);

        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void givenInvalidId_whenGetOrganization_thenShouldReturnBadRequest() {
        ResponseEntity<OrganizationResponse> forEntity = restTemplate.getForEntity(
                "/organization/" + 1, OrganizationResponse.class);

        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
