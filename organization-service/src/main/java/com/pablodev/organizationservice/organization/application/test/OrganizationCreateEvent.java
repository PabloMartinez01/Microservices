package com.pablodev.organizationservice.organization.application.test;


public class OrganizationCreateEvent extends OrganizationDomainEvent {

    @Override
    public String getEventName() {
        return "organization.create";
    }
}
