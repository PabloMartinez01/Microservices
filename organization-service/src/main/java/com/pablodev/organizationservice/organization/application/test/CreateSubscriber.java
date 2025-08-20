package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;
import org.springframework.stereotype.Component;

@Component
public class CreateSubscriber implements DomainSubscriber<OrganizationCreateEvent> {

    @Override
    public void on(OrganizationCreateEvent event) {
        System.out.println("OrganizationCreateEvent: OrganizationCreateEvent received");
    }

}
