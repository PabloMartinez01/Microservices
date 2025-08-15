package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import java.util.Properties;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerEndpointFactory {

    private final ApplicationContext applicationContext;
    private final KafkaProperties kafkaProperties;
    private final DefaultMessageHandlerMethodFactory methodFactory;
    private final DomainEventsRegistry eventsRegistry;


    public MethodKafkaListenerEndpoint<String, DomainEvent> defaultListenerEndpoint(Class<?> subscriberClass,
            Class<? extends DomainEvent> eventClass) throws NoSuchMethodException {

        Properties properties = new Properties();
        properties.setProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, eventClass.getName());

        String groupId = kafkaProperties.getConsumer().getGroupId();
        String topic = eventsRegistry.getEventNameOf(eventClass);

        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();
        endpoint.setId(UUID.randomUUID().toString());
        endpoint.setGroupId(groupId);
        endpoint.setAutoStartup(true);
        endpoint.setConsumerProperties(properties);
        endpoint.setTopics(topic);
        endpoint.setMessageHandlerMethodFactory(methodFactory);
        endpoint.setBean(applicationContext.getBean(subscriberClass));
        endpoint.setMethod(subscriberClass.getMethod("on", eventClass));

        return endpoint;
    }

}
