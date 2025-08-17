package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;

@DomainSubscriber(OrganizationCreateEvent.class)
public class CreateSubscriber {

    public void on(OrganizationCreateEvent event) {
        System.out.println("OrganizationCreateEvent: OrganizationCreateEvent received");
    }

}
