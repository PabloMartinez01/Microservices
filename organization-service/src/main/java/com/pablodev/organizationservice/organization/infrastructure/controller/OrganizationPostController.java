package com.pablodev.organizationservice.organization.infrastructure.controller;

import com.pablodev.organizationservice.organization.application.create.CreateOrganizationCommand;
import com.pablodev.organizationservice.organization.infrastructure.controller.dto.CreateOrganizationRequest;
import com.pablodev.shared.domain.command.CommandBus;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationPostController {

    private final CommandBus commandBus;

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

        commandBus.dispatch(applicationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}









