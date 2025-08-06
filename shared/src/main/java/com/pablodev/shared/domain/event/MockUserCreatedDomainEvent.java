package com.pablodev.shared.domain.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Topic("user.created")
public class MockUserCreatedDomainEvent extends DomainEvent {

    private final String email;

}
