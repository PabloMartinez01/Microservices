package com.pablodev.organizationservice.subscription.infrastructure.controller;

import com.pablodev.organizationservice.subscription.application.SubscriptionResponse;
import com.pablodev.organizationservice.subscription.application.SubscriptionsResponse;
import com.pablodev.organizationservice.subscription.application.find.FindSubscriptionQuery;
import com.pablodev.organizationservice.subscription.application.find_by_organization.FindSubscriptionByOrganizationQuery;
import com.pablodev.shared.domain.query.QueryBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionGetController {

    private final QueryBus queryBus;

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(queryBus.ask(new FindSubscriptionQuery(id)));
    }

    @GetMapping(params = {"organizationId"})
    public ResponseEntity<SubscriptionsResponse> findByOrganizationId(@RequestParam String organizationId) {
        FindSubscriptionByOrganizationQuery query = new FindSubscriptionByOrganizationQuery(organizationId);
        return ResponseEntity.ok(queryBus.ask(query));
    }


}
