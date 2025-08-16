package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.stereotype.Component;

@Component
public abstract class KafkaListenerEndpointCustomizer {

    private Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners;

    public void customize(Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners) {
        this.listeners = listeners;
        customize(new KafkaListenerEndpointConfigurer(listeners));
    }

    public abstract void customize(KafkaListenerEndpointConfigurer configurer);


    @RequiredArgsConstructor
    public static class KafkaListenerEndpointConfigurer {

        private final Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners;

        public void configureListeners(Consumer<KafkaListenerEndpointsConfiguration> consumer) {
            consumer.accept(new KafkaListenerEndpointsConfiguration(listeners));
        }

    }

    @RequiredArgsConstructor
    public static class KafkaListenerEndpointsConfiguration {

        private final Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners;

        public KafkaListenerEndpointConfiguration listener(Class<?> clazz) {
            MethodKafkaListenerEndpoint<String, DomainEvent> listener =
                    Optional.ofNullable(listeners.get(clazz)).orElseThrow(
                            () -> new RuntimeException("No listener found for class: " + clazz.getName()));
            return new KafkaListenerEndpointConfiguration(this, listener);
        }

    }

    @RequiredArgsConstructor
    public static class KafkaListenerEndpointConfiguration {

        private final KafkaListenerEndpointsConfiguration listenersConfigurer;
        private final MethodKafkaListenerEndpoint<String, DomainEvent> listener;

        public KafkaListenerEndpointConfiguration groupId(String groupId) {
            listener.setGroupId(groupId);
            return this;
        }

        public KafkaListenerEndpointConfiguration concurrency(int concurrency) {
            listener.setConcurrency(concurrency);
            return this;
        }

        public KafkaListenerEndpointConfiguration listener(Class<?> clazz) {
            return listenersConfigurer.listener(clazz);
        }

    }


}
