package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerInitializer {

    private final DomainEventsRegistry eventsRegistry;
    private final List<KafkaConsumerRegistrationCustomizer> customizers;
    private final KafkaConsumerRegistrar registrar;

    @PostConstruct
    public void postConstruct() throws NoSuchMethodException {

        Map<Class<?>, KafkaConsumerRegistration> consumers = new HashMap<>();
        Reflections reflections = new Reflections("com.pablodev");
        Set<Class<?>> subscriberClasses = reflections.getTypesAnnotatedWith(DomainSubscriber.class);

        for (Class<?> subscriberClass : subscriberClasses) {

            DomainSubscriber subscriberAnnotation = subscriberClass.getAnnotation(DomainSubscriber.class);
            Class<? extends DomainEvent> eventClass = subscriberAnnotation.value();

            String topic = eventsRegistry.getEventNameOf(eventClass);

            KafkaConsumerRegistration consumerRegistration = KafkaConsumerRegistration.builder()
                    .subscriberClass(subscriberClass)
                    .eventClass(eventClass)
                    .topic(topic)
                    .build();

            consumers.put(subscriberClass, consumerRegistration);

        }

        customizers.forEach(customizer ->
                customizer.customize(consumers)
        );

        registrar.registerKafkaConsumers(consumers.values().stream().toList());

    }


}
