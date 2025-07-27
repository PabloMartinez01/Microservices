package com.pablodev.organizationservice.organization.infrastructure.controller;

import com.pablodev.organizationservice.organization.application.update.UpdateOrganizationCommand;
import com.pablodev.organizationservice.organization.infrastructure.controller.dto.update.UpdateOrganizationRequest;
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
    public ResponseEntity<Void> update(@PathVariable String id,
            @RequestBody UpdateOrganizationRequest request) {

        UpdateOrganizationCommand command = new UpdateOrganizationCommand(
                id,
                request.name(),
                request.type(),
                request.address().street(),
                request.address().city(),
                request.address().state(),
                request.address().country()
        );

        commandBus.dispatch(command);

        return ResponseEntity.ok().build();
    }

}
