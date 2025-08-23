package com.pablodev.shared.infrastructure.event.kafka.registration;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import java.util.Properties;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerEndpointFactory {

    private final ApplicationContext applicationContext;
    private final DefaultMessageHandlerMethodFactory methodFactory;
    private final KafkaListenerGroupIdResolver groupIdResolver;
    private final KafkaListenerTopicResolver topicResolver;

    private static Properties defaultProperties(Class<? extends DomainEvent> eventClass) {
        Properties properties = new Properties();
        properties.setProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, eventClass.getName());
        return properties;
    }

    public MethodKafkaListenerEndpoint<String, DomainEvent> createListenerEndpoint(
            Class<? extends DomainSubscriber<?>> subscriberClass,
            Class<? extends DomainEvent> eventClass
    ) throws NoSuchMethodException {

        MethodKafkaListenerEndpoint<String, DomainEvent> listener = new MethodKafkaListenerEndpoint<>();

        listener.setId(UUID.randomUUID().toString());
        listener.setGroupId(groupIdResolver.resolve(subscriberClass));
        listener.setTopicPattern(topicResolver.resolve(eventClass));
        listener.setAutoStartup(true);
        listener.setConsumerProperties(defaultProperties(eventClass));
        listener.setMessageHandlerMethodFactory(methodFactory);
        listener.setBean(applicationContext.getBean(subscriberClass));
        listener.setMethod(subscriberClass.getMethod("on", eventClass));
        
        return listener;
    }


}
