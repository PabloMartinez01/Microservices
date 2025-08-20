package com.pablodev.shared.infrastructure.event.exceptions;

public class DomainEventDestinationNotFound extends RuntimeException {

    public DomainEventDestinationNotFound(String name) {
        super("No event destination found for %s".formatted(name));
    }

}
