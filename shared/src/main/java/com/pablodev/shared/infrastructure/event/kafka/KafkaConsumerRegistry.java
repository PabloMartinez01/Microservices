package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerRegistry {

    private final DomainEventsRegistry eventsRegistry;
    private Map<Class<?>, KafkaConsumerRegistration> consumers;

    @PostConstruct
    public void postConstruct() throws NoSuchMethodException {

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

    }


}
