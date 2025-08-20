package com.pablodev.shared.infrastructure.event.exceptions;

public class DomainEventDestinationNotFoundException extends RuntimeException {

    public DomainEventDestinationNotFoundException(String name) {
        super("No event destination found for %s".formatted(name));
    }

}
