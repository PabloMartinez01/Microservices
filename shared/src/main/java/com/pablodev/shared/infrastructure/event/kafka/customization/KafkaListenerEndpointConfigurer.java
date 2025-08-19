package com.pablodev.shared.infrastructure.event.kafka.customization;

import com.pablodev.shared.domain.event.AbstractDomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;

@AllArgsConstructor
public class KafkaListenerEndpointConfigurer {

    private final KafkaListenerEndpointsConfigurer listenersConfigurer;
    private final MethodKafkaListenerEndpoint<String, AbstractDomainEvent> listener;

    public KafkaListenerEndpointConfigurer groupId(String groupId) {
        listener.setGroupId(groupId);
        return this;
    }

    public KafkaListenerEndpointConfigurer concurrency(int concurrency) {
        listener.setConcurrency(concurrency);
        return this;
    }

    public KafkaListenerEndpointConfigurer listener(Class<?> clazz) {
        return listenersConfigurer.listener(clazz);
    }

}
