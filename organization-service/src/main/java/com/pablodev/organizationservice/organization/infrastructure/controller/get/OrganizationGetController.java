package com.pablodev.organizationservice.organization.infrastructure.controller.get;

import com.pablodev.organizationservice.organization.application.find.FindOrganizationQuery;
import com.pablodev.organizationservice.organization.application.find.OrganizationFinder;
import com.pablodev.organizationservice.organization.application.OrganizationResponse;
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

    private final OrganizationFinder finder;

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> find(@PathVariable String id) {
        FindOrganizationQuery query = new FindOrganizationQuery(id);
        OrganizationResponse response = finder.find(query);
        return ResponseEntity.ok(response);
    }

}
