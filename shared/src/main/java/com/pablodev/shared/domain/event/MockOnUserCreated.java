package com.pablodev.shared.domain.event;

@DomainSubscriber(MockUserCreatedDomainEvent.class)
public class MockOnUserCreated {

    public void on(MockUserCreatedDomainEvent event) {
        System.out.println("received");
    }

}
