package com.pablodev.organizationservice.organization.infrastructure.controller;

import com.pablodev.organizationservice.organization.application.create.CreateOrganizationCommand;
import com.pablodev.organizationservice.organization.infrastructure.controller.dto.create.CreateOrganizationRequest;
import com.pablodev.organizationservice.organization.infrastructure.controller.mapper.OrganizationRequestMapper;
import com.pablodev.shared.domain.command.CommandBus;
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
    private final OrganizationRequestMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOrganizationRequest request) {
        CreateOrganizationCommand command = mapper.toCreateOrganizationCommand(request);
        commandBus.dispatch(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}









