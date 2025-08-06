package com.pablodev.shared.domain.event;

import com.pablodev.shared.infrastructure.event.kafka.UserCreatedDomainEvent;

@DomainSubscriber(UserCreatedDomainEvent.class)
public class MockOnUserCreated {

}
