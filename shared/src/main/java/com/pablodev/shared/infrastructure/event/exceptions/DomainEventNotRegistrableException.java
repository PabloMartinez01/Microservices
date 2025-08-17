package com.pablodev.shared.infrastructure.event.exceptions;

public class DomainEventNotRegistrableException extends RuntimeException {

    public DomainEventNotRegistrableException(String message, Throwable cause) {
        super(message, cause);
    }
}
