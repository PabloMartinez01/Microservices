package com.pablodev.organizationservice.organization.application.user;

import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.infrastructure.event.kafka.UserCreatedDomainEvent;

@DomainSubscriber(UserCreatedDomainEvent.class)
public class CreateInternalUserOnUserCreated {

}
