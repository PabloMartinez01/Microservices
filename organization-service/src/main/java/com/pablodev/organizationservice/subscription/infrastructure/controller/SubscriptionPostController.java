package com.pablodev.organizationservice.subscription.infrastructure.controller;

import com.pablodev.organizationservice.subscription.application.cancel.CancelSubscriptionCommand;
import com.pablodev.organizationservice.subscription.application.create.CreateSubscriptionCommand;
import com.pablodev.shared.domain.command.CommandBus;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionPostController {

    private final CommandBus commandBus;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateSubscriptionRequest request) {

        CreateSubscriptionCommand command = new CreateSubscriptionCommand(
                UUID.randomUUID().toString(),
                request.organizationId()
        );

        commandBus.dispatch(command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String id) {
        commandBus.dispatch(new CancelSubscriptionCommand(id));
        return ResponseEntity.ok().build();
    }

}
