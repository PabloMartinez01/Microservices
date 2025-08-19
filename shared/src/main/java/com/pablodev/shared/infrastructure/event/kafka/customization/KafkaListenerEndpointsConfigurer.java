package com.pablodev.shared.infrastructure.event.kafka.customization;

import com.pablodev.shared.domain.event.AbstractDomainEvent;
import com.pablodev.shared.infrastructure.event.kafka.exception.ListenerNotFoundException;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;

@AllArgsConstructor
public class KafkaListenerEndpointsConfigurer {

    private final Map<Class<?>, MethodKafkaListenerEndpoint<String, AbstractDomainEvent>> listeners;

    public KafkaListenerEndpointConfigurer listener(Class<?> clazz) {
        MethodKafkaListenerEndpoint<String, AbstractDomainEvent> listener = Optional.ofNullable(
                        listeners.get(clazz))
                .orElseThrow(() -> new ListenerNotFoundException(clazz.getName()));
        return new KafkaListenerEndpointConfigurer(this, listener);
    }

}
