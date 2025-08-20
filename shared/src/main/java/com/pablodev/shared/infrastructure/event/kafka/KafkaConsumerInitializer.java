package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.DomainSubscriberInformation;
import com.pablodev.shared.infrastructure.event.DomainSubscribersRegistry;
import com.pablodev.shared.infrastructure.event.kafka.customization.KafkaListenerEndpointCustomizer;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerInitializer {

    private final Optional<KafkaListenerEndpointCustomizer> listenerCustomizer;
    private final KafkaListenerEndpointRegistrar listenerRegistrar;
    private final KafkaListenerEndpointFactory listenerFactory;
    private final DomainSubscribersRegistry subscribersRegistry;

    @PostConstruct
    public void postConstruct() throws NoSuchMethodException {

        Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> consumers = new HashMap<>();

        for (DomainSubscriberInformation subscriber : subscribersRegistry.getSubscribers()) {
            Class<?> subscriberClass = subscriber.getSubscriberClass();
            MethodKafkaListenerEndpoint<String, DomainEvent> listener =
                    listenerFactory.defaultListenerEndpoint(subscriberClass, subscriber.getEventClass());

            consumers.put(subscriberClass, listener);
        }
        listenerCustomizer.ifPresent(customizer -> customizer.customize(consumers));
        consumers.values().forEach(listenerRegistrar::registerEndpoint);
    }


}
