package com.pablodev.organizationservice.organization.infrastructure.controller.post;

import com.pablodev.organizationservice.organization.application.create.CreateOrganizationCommand;
import com.pablodev.organizationservice.organization.application.create.OrganizationCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationPostController {

    private final OrganizationCreator creator;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOrganizationRequest httpRequest) {

        CreateOrganizationCommand applicationRequest = new CreateOrganizationCommand(
                UUID.randomUUID().toString(),
                httpRequest.name(),
                httpRequest.type(),
                httpRequest.address().street(),
                httpRequest.address().city(),
                httpRequest.address().state(),
                httpRequest.address().country()
        );

        creator.create(applicationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}









