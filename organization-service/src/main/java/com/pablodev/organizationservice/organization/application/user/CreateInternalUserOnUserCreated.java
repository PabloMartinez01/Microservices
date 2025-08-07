package com.pablodev.organizationservice.organization.application.user;

import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.domain.event.MockUserCreatedDomainEvent;

@DomainSubscriber(MockUserCreatedDomainEvent.class)
public class CreateInternalUserOnUserCreated {

    public void on(MockUserCreatedDomainEvent event) {
        System.out.println("received " + event.getId());
    }

}
