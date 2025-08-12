package com.pablodev.shared.infrastructure.event.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablodev.shared.infrastructure.event.DomainEventSubscribersRegistry;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;


@Slf4j
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaEventBusConfiguration {

    private final ObjectMapper objectMapper;
    private final DomainEventsRegistry eventsRegistry;
    private final DomainEventSubscribersRegistry subscribersRegistry;
    private final ApplicationContext applicationContext;

    /*
    @KafkaListener(topicPattern = ".*")
    public void consume(
            String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
            throws JsonProcessingException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<? extends DomainEvent> eventClass = eventsRegistry.getDomainEvent(topic);
        List<Class<?>> subscribers = subscribersRegistry.getSubscribersForEvent(eventClass);

        DomainEvent domainEvent = objectMapper.readValue(message, eventClass);

        for (Class<?> subscriber : subscribers) {
            Method onMethod = subscriber.getDeclaredMethod("on", domainEvent.getClass());
            Object subscriberInstance = applicationContext.getBean(subscriber);
            onMethod.invoke(subscriberInstance, domainEvent);
        }

    }

     */


}

