package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;
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

    private static final String TOPIC_REGEX = "^[a-zA-Z0-9._-]+$";

    private final ApplicationContext applicationContext;
    private final KafkaProperties kafkaProperties;
    private final DefaultMessageHandlerMethodFactory methodFactory;
    private final DomainEventsRegistry eventsRegistry;

    private static Properties defaultProperties(Class<? extends DomainEvent> eventClass) {
        Properties properties = new Properties();
        properties.setProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, eventClass.getName());
        return properties;
    }

    public MethodKafkaListenerEndpoint<String, DomainEvent> defaultListenerEndpoint(
            Class<?> subscriberClass,
            Class<? extends DomainEvent> eventClass
    ) throws NoSuchMethodException {

        String groupId = kafkaProperties.getConsumer().getGroupId();
        String topic = eventsRegistry.getEventNameOf(eventClass);

        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();
        endpoint.setId(UUID.randomUUID().toString());
        endpoint.setGroupId(groupId);
        endpoint.setAutoStartup(true);
        endpoint.setConsumerProperties(defaultProperties(eventClass));
        endpoint.setMessageHandlerMethodFactory(methodFactory);
        endpoint.setBean(applicationContext.getBean(subscriberClass));
        endpoint.setMethod(subscriberClass.getMethod("on", eventClass));

        registerTopicOrPattern(topic, endpoint);

        return endpoint;
    }

    private void registerTopicOrPattern(String topic,
            MethodKafkaListenerEndpoint<String, DomainEvent> endpoint) {
        if (isTopicPattern(topic)) {
            endpoint.setTopicPattern(Pattern.compile(topic));
        } else {
            endpoint.setTopics(topic);
        }
    }

    private boolean isTopicPattern(String topic) {
        return !topic.matches(TOPIC_REGEX);
    }


}
