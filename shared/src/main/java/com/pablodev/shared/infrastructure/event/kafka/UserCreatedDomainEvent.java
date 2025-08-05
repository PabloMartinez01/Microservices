package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreatedDomainEvent extends DomainEvent {

    private final String email;

    @Override
    public String getEventName() {
        return "user.created";
    }
}
