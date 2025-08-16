package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class KafkaListenerEndpointCustomizer {

    private final Consumer<KafkaListenerEndpointsConfigurer> consumer;

    public void customize(Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners) {
        consumer.accept(new KafkaListenerEndpointsConfigurer(listeners));
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Component
    public static class KafkaListenerEndpointCustomizerBuilder {

        private Consumer<KafkaListenerEndpointsConfigurer> consumer;

        public KafkaListenerEndpointCustomizerBuilder configureListeners(
                Consumer<KafkaListenerEndpointsConfigurer> consumer) {
            return new KafkaListenerEndpointCustomizerBuilder(consumer);
        }

        public KafkaListenerEndpointCustomizer build() {
            return new KafkaListenerEndpointCustomizer(consumer);
        }

    }

    @RequiredArgsConstructor
    public static class KafkaListenerEndpointsConfigurer {

        private final Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> listeners;

        public KafkaListenerEndpointConfigurer listener(Class<?> clazz) {
            MethodKafkaListenerEndpoint<String, DomainEvent> listener =
                    Optional.ofNullable(listeners.get(clazz)).orElseThrow(
                            () -> new RuntimeException("No listener found for class: " + clazz.getName()));
            return new KafkaListenerEndpointConfigurer(this, listener);
        }

    }

    @RequiredArgsConstructor
    public static class KafkaListenerEndpointConfigurer {

        private final KafkaListenerEndpointsConfigurer listenersConfigurer;
        private final MethodKafkaListenerEndpoint<String, DomainEvent> listener;

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


}
