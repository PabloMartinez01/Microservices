package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.DomainEventSubscriberInformation;
import com.pablodev.shared.infrastructure.event.DomainEventSubscribersRegistry;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;


@Slf4j
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaEventBusConfiguration {

    private final ApplicationContext applicationContext;
    private final KafkaListenerEndpointRegistry registry;
    private final KafkaProperties kafkaProperties;
    private final DomainEventSubscribersRegistry subscribersRegistry;
    private final DomainEventsRegistry domainEventsRegistry;
    private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DomainEvent>> kafkaListenerContainerFactory;
    private final DefaultMessageHandlerMethodFactory messageHandlerMethodFactory;

    @PostConstruct
    public void init() throws NoSuchMethodException {

        //initializeMessageHandlerMethodFactory();

        for (DomainEventSubscriberInformation subscriberInformation : subscribersRegistry.getSubscribersInformation()) {
            Class<?> subscriber = subscriberInformation.getSubscriber();
            List<Class<? extends DomainEvent>> events = subscriberInformation.getEvents();
            registerKafkaListener(subscriber, events);
        }

    }


    private String[] getEventNames(List<Class<? extends DomainEvent>> events) {
        return events.stream()
                .map(domainEventsRegistry::getEventNameOf)
                .toArray(String[]::new);
    }

    private Properties createConsumerPropertiesForEvent(Class<? extends DomainEvent> event) {
        Properties properties = new Properties();
        properties.setProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, event.getName());
        return properties;
    }

    private void registerKafkaListener(Class<?> subscriber, List<Class<? extends DomainEvent>> events)
            throws NoSuchMethodException {

        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();
        endpoint.setId(UUID.randomUUID().toString());
        endpoint.setGroupId(kafkaProperties.getConsumer().getGroupId());
        endpoint.setAutoStartup(true);
        endpoint.setTopics(getEventNames(events));
        endpoint.setConsumerProperties(createConsumerPropertiesForEvent(events.getFirst()));
        endpoint.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
        endpoint.setBean(applicationContext.getBean(subscriber));
        endpoint.setMethod(subscriber.getMethod("on", events.getFirst()));
        registry.registerListenerContainer(endpoint, kafkaListenerContainerFactory, true);


    }


}

