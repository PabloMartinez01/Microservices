package com.pablodev.shared.infrastructure.event.kafka.exception;

public class KafkaListenerNotFoundException extends RuntimeException {

    public KafkaListenerNotFoundException(String listenerName) {
        super("No listener found for class: %s".formatted(listenerName));
    }
}
