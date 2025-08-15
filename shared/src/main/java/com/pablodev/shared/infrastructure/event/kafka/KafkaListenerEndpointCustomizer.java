package com.pablodev.shared.infrastructure.event.kafka;

import java.util.Map;
import org.springframework.kafka.config.KafkaListenerEndpoint;

public interface KafkaListenerEndpointCustomizer {

    void customize(Map<Class<?>, KafkaListenerEndpoint> listenerEndpoints);
}
