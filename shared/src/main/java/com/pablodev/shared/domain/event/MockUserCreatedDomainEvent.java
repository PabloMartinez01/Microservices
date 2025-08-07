package com.pablodev.shared.domain.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MockUserCreatedDomainEvent extends DomainEvent {

    private final String email;

    @Override
    public String getEventName() {
        return "user.created";
    }
}
