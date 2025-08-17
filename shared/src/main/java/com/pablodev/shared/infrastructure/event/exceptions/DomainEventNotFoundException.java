package com.pablodev.shared.infrastructure.event.exceptions;

public class DomainEventNotFoundException extends RuntimeException {

    public DomainEventNotFoundException(String name) {
        super("No event found for %s".formatted(name));
    }
}
