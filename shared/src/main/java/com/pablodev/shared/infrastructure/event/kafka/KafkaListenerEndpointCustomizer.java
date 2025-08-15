package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.stereotype.Component;

@Component
public abstract class KafkaListenerEndpointCustomizer {

    private Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners;

    public void customize(Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners) {
        this.listeners = listeners;
        customize();
    }

    public abstract void customize();

    protected ListenerBuilder listener(Class<?> clazz) {
        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = listeners.get(clazz);
        if (endpoint == null) {
            throw new IllegalArgumentException("No listener found for class: " + clazz.getName());
        }
        return new ListenerBuilder(endpoint);
    }


    @RequiredArgsConstructor
    protected static class ListenerBuilder {

        private final MethodKafkaListenerEndpoint<String, DomainEvent> endpoint;
        

        public ListenerBuilder groupId(String groupId) {
            endpoint.setGroupId(groupId);
            return this;
        }

        public ListenerBuilder concurrency(int concurrency) {
            endpoint.setConcurrency(concurrency);
            return this;
        }

        public MethodKafkaListenerEndpoint<String, DomainEvent> build() {
            return endpoint;
        }
    }

}
