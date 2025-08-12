package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.infrastructure.event.DomainEventSubscribersRegistry;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import jakarta.annotation.PostConstruct;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerRegistrar {

    private final DomainEventsRegistry eventsRegistry;
    private final ApplicationContext applicationContext;
    private final ConsumerFactory<String, DomainEvent> consumerFactory;
    private final KafkaListenerEndpointRegistry registry;
    private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DomainEvent>> kafkaListenerContainerFactory;
    private final KafkaProperties kafkaProperties;
    private final DomainEventSubscribersRegistry subscribersRegistry;
    private final DomainEventsRegistry domainEventsRegistry;


    @PostConstruct
    public void postConstruct() throws NoSuchMethodException {

        Reflections reflections = new Reflections("com.pablodev");
        Set<Class<?>> subscriberClasses = reflections.getTypesAnnotatedWith(DomainSubscriber.class);

        for (Class<?> subscriberClass : subscriberClasses) {

            DomainSubscriber subscriberAnnotation = subscriberClass.getAnnotation(DomainSubscriber.class);
            Class<? extends DomainEvent> eventClass = subscriberAnnotation.value();

            String topic = eventsRegistry.getEventNameOf(eventClass);
            Object subscriber = applicationContext.getBean(subscriberClass);

            DefaultMessageHandlerMethodFactory methodFactory = new DefaultMessageHandlerMethodFactory();
            methodFactory.setMessageConverter(new MappingJackson2MessageConverter());
            methodFactory.afterPropertiesSet();

            Properties properties = new Properties();
            properties.setProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, eventClass.getName());

            MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();
            endpoint.setId(UUID.randomUUID().toString());
            endpoint.setGroupId(kafkaProperties.getConsumer().getGroupId());
            endpoint.setAutoStartup(true);
            endpoint.setConsumerProperties(properties);
            endpoint.setTopics(topic);
            endpoint.setMessageHandlerMethodFactory(methodFactory);
            endpoint.setBean(subscriber);
            endpoint.setMethod(subscriberClass.getMethod("on", eventClass));
            registry.registerListenerContainer(endpoint, kafkaListenerContainerFactory);
        }

    }

}
