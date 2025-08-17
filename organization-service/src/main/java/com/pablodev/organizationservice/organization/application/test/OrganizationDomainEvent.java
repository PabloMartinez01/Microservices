package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainEvent;

public class OrganizationDomainEvent extends DomainEvent {

    @Override
    public String getEventName() {
        return "pablodev.microservices.organization.organization.*";
    }
}
