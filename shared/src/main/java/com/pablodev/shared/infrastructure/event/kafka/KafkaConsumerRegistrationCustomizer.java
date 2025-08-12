package com.pablodev.shared.infrastructure.event.kafka;

import java.util.Map;

public interface KafkaConsumerRegistrationCustomizer {

    void customize(Map<Class<?>, KafkaConsumerRegistration> registrations);
}
