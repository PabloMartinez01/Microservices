package com.pablodev.organizationservice.subscription.infrastructure.controller;

import com.pablodev.organizationservice.subscription.application.SubscriptionResponse;
import com.pablodev.organizationservice.subscription.application.find.FindSubscriptionQuery;
import com.pablodev.shared.domain.query.QueryBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations")
@RequiredArgsConstructor
public class SubscriptionGetController {

    private final QueryBus queryBus;

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(queryBus.ask(new FindSubscriptionQuery(id)));
    }

}
