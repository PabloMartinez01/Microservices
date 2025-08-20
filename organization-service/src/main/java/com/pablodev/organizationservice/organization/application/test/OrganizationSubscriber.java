package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;
import org.springframework.stereotype.Component;

@Component
public class OrganizationSubscriber implements DomainSubscriber<OrganizationDomainEvent> {

    @Override
    public void on(OrganizationDomainEvent event) {
        System.out.println("OrganizationDomainEvent: OrganizationDomainEvent received");
    }

}
