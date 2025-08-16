package com.pablodev.shared.infrastructure.event.kafka.customization;

import com.pablodev.shared.domain.event.DomainEvent;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;

@AllArgsConstructor
public class KafkaListenerEndpointsConfigurer {

    private final Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners;

    public KafkaListenerEndpointConfigurer listener(Class<?> clazz) {
        MethodKafkaListenerEndpoint<String, DomainEvent> listener = Optional.ofNullable(listeners.get(clazz))
                .orElseThrow(() -> new RuntimeException(
                        "No listener found for class: %s".formatted(clazz.getName())));
        
        return new KafkaListenerEndpointConfigurer(this, listener);
    }

}
