package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;

@DomainSubscriber(OrganizationDomainEvent.class)
public class OrganizationSubscriber {

    public void on(OrganizationDomainEvent event) {
        System.out.println("OrganizationDomainEvent: OrganizationDomainEvent received");
    }

}
