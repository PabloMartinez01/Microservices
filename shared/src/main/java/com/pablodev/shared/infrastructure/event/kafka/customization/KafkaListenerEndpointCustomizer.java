package com.pablodev.shared.infrastructure.event.kafka.customization;

import com.pablodev.shared.domain.event.DomainEvent;
import java.util.Map;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;


@AllArgsConstructor
public class KafkaListenerEndpointCustomizer {

    private final Consumer<KafkaListenerEndpointsConfigurer> consumer;

    public void customize(Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners) {
        consumer.accept(new KafkaListenerEndpointsConfigurer(listeners));
    }

}
