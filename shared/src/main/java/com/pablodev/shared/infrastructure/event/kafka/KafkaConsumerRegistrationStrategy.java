package com.pablodev.shared.infrastructure.event.kafka;

import java.util.List;

public interface KafkaConsumerRegistrationStrategy {

    void register(List<KafkaConsumerRegistration> kafkaConsumerRegistrations);
}
