package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;

@DomainSubscriber(OrganizationDeleteEvent.class)
public class DeleteSubscriber {

    public void on(OrganizationDeleteEvent event) {
        System.out.println("Delete Subscriber: OrganizationDeleteEvent received");
    }

}
