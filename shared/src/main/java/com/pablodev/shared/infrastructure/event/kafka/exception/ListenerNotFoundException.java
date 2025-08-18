package com.pablodev.shared.infrastructure.event.kafka.exception;

public class ListenerNotFoundException extends RuntimeException {

    public ListenerNotFoundException(String listenerName) {
        super("No listener found for class: %s".formatted(listenerName));
    }
}
