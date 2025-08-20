package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;
import org.springframework.stereotype.Component;

@Component
public class DeleteSubscriber implements DomainSubscriber<OrganizationDeleteEvent> {

    @Override
    public void on(OrganizationDeleteEvent event) {
        System.out.println("Delete Subscriber: OrganizationDeleteEvent received");
    }

}
