package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.AbstractDomainEvent;
import com.pablodev.shared.infrastructure.event.DomainEventSubscriberInformation;
import com.pablodev.shared.infrastructure.event.DomainEventSubscribersRegistry;
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
    private final DomainEventSubscribersRegistry subscribersRegistry;

    @PostConstruct
    public void postConstruct() throws NoSuchMethodException {

        Map<Class<?>, MethodKafkaListenerEndpoint<String, AbstractDomainEvent>> consumers = new HashMap<>();

        for (DomainEventSubscriberInformation subscriber : subscribersRegistry.getSubscribers()) {
            Class<?> subscriberClass = subscriber.getSubscriberClass();
            MethodKafkaListenerEndpoint<String, AbstractDomainEvent> listener =
                    listenerFactory.defaultListenerEndpoint(subscriberClass, subscriber.getEventClass());

            consumers.put(subscriberClass, listener);
        }
        listenerCustomizer.ifPresent(customizer -> customizer.customize(consumers));
        consumers.values().forEach(listenerRegistrar::registerEndpoint);
    }


}
