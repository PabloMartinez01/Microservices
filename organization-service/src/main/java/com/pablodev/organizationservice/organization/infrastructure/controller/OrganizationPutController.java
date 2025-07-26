package com.pablodev.organizationservice.organization.infrastructure.controller;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.shared.domain.command.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationPutController {

    private final CommandBus commandBus;

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrganization(@PathVariable String id,
            @RequestBody Organization organization) {

        return ResponseEntity.ok().build();
    }

}
