package com.pablodev.shared.domain.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MockUserCreatedDomainEvent extends DomainEvent {

    private final String email;

    @Override
    public String getEventName() {
        return "user.created";
    }
}
