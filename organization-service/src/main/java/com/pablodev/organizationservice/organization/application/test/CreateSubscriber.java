package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;
import org.springframework.stereotype.Component;

@Component
public class CreateSubscriber implements DomainSubscriber<OrganizationCreateEvent> {

    @Override
    public void on(OrganizationCreateEvent event) {
        System.out.println("Trying to consume organization create event");
        throw new RuntimeException("Unexpected exception");
    }

}
