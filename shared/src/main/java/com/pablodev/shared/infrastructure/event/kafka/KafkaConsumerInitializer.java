package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.infrastructure.event.kafka.customization.KafkaListenerEndpointCustomizer;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerInitializer {

    private final Optional<KafkaListenerEndpointCustomizer> customizer;
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

        customizer.ifPresent(c -> c.customize(consumers));
        consumers.values().forEach(registrar::registerEndpoint);
    }


}
