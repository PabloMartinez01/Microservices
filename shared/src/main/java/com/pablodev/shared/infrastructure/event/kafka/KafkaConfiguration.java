package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.MockUserCreatedDomainEvent;
import com.pablodev.shared.infrastructure.event.DomainEventSubscriberInformation;
import com.pablodev.shared.infrastructure.event.DomainEventSubscribersRegistry;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;


@Slf4j
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaListenerEndpointRegistry registry;
    private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DomainEvent>> kafkaListenerContainerFactory;
    private final KafkaProperties kafkaProperties;
    private final DomainEventSubscribersRegistry subscribersRegistry;
    private final DomainEventsRegistry domainEventsRegistry;


    @PostConstruct
    public void init() throws NoSuchMethodException {

        for (DomainEventSubscriberInformation subscriberInformation : subscribersRegistry.getSubscribersInformation()) {

            Class<?> subscriber = subscriberInformation.getSubscriber();
            List<Class<? extends DomainEvent>> events = subscriberInformation.getEvents();

            String[] eventNames = events.stream()
                    .map(domainEventsRegistry::getEventNameOf)
                    .toArray(String[]::new);

            registerKafkaListener(subscriber, eventNames);

            log.info("Registered {} subscriber for {} topics",
                    subscriber.getTypeName().substring(subscriber.getTypeName().lastIndexOf(".") + 1),
                    Arrays.toString(eventNames)
            );
        }


    }

    private void registerKafkaListener(Class<?> subscriber, String[] eventNames)
            throws NoSuchMethodException {
        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();
        endpoint.setId(UUID.randomUUID().toString());
        endpoint.setGroupId(kafkaProperties.getConsumer().getGroupId());
        endpoint.setAutoStartup(true);
        endpoint.setTopics(eventNames);
        endpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory());
        endpoint.setBean(subscriber);
        endpoint.setMethod(subscriber.getMethod("on", MockUserCreatedDomainEvent.class));
        registry.registerListenerContainer(endpoint, kafkaListenerContainerFactory);
    }


}
