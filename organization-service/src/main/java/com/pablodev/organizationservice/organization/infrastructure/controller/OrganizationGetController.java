package com.pablodev.organizationservice.organization.infrastructure.controller;

import com.pablodev.organizationservice.organization.application.OrganizationResponse;
import com.pablodev.organizationservice.organization.application.OrganizationsResponse;
import com.pablodev.organizationservice.organization.application.find.FindOrganizationQuery;
import com.pablodev.organizationservice.organization.application.findAll.FindAllOrganizationsQuery;
import com.pablodev.shared.domain.query.QueryBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/organization")
public class OrganizationGetController {

    private final QueryBus queryBus;

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> find(@PathVariable String id) {
        FindOrganizationQuery query = new FindOrganizationQuery(id);
        OrganizationResponse response = queryBus.ask(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<OrganizationsResponse> findAll() {
        OrganizationsResponse response = queryBus.ask(new FindAllOrganizationsQuery());
        return ResponseEntity.ok(response);
    }

}
