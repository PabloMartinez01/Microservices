package com.pablodev.organizationservice.organization.application.test;

public class OrganizationDeleteEvent extends OrganizationDomainEvent {

    @Override
    public String getEventName() {
        return "pablodev.microservices.organization.organization.delete";
    }
}
