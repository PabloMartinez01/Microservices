package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerInitializer {

    private final List<KafkaListenerEndpointCustomizer> customizers;
    private final KafkaListenerEndpointRegistrar registrar;
    private final KafkaListenerEndpointFactory listenerEndpointFactory;

    @PostConstruct
    public void postConstruct() throws NoSuchMethodException {

        Map<Class<?>, MethodKafkaListenerEndpoint<String, DomainEvent>> consumers = new HashMap<>();

        Reflections reflections = new Reflections("com.pablodev");
        Set<Class<?>> subscriberClasses = reflections.getTypesAnnotatedWith(DomainSubscriber.class);

        for (Class<?> subscriberClass : subscriberClasses) {

            DomainSubscriber subscriberAnnotation = subscriberClass.getAnnotation(DomainSubscriber.class);
            Class<? extends DomainEvent> eventClass = subscriberAnnotation.value();

            MethodKafkaListenerEndpoint<String, DomainEvent> endpoint =
                    listenerEndpointFactory.defaultListenerEndpoint(subscriberClass, eventClass);
            consumers.put(subscriberClass, endpoint);
        }

        consumers.values().forEach(registrar::registerEndpoint);
    }


}
