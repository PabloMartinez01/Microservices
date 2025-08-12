package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerRegistrar {

    private final ApplicationContext applicationContext;
    private final KafkaListenerEndpointRegistry registry;
    private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DomainEvent>> kafkaListenerContainerFactory;
    private final KafkaProperties kafkaProperties;
    private final DefaultMessageHandlerMethodFactory methodFactory;


    public void registerKafkaConsumer(KafkaConsumerRegistration consumerRegistration)
            throws NoSuchMethodException {

        Class<?> subscriberClass = consumerRegistration.getSubscriberClass();
        Class<? extends DomainEvent> eventClass = consumerRegistration.getEventClass();

        Properties properties = new Properties();
        properties.setProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, eventClass.getName());
        
        String groupId = Optional.ofNullable(consumerRegistration.getGroupId())
                .orElse(kafkaProperties.getConsumer().getGroupId());

        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();
        endpoint.setId(UUID.randomUUID().toString());
        endpoint.setGroupId(groupId);
        endpoint.setAutoStartup(true);
        endpoint.setConsumerProperties(properties);
        endpoint.setTopics(consumerRegistration.getTopic());
        endpoint.setMessageHandlerMethodFactory(methodFactory);

        endpoint.setBean(applicationContext.getBean(subscriberClass));
        endpoint.setMethod(subscriberClass.getMethod("on", eventClass));
        registry.registerListenerContainer(endpoint, kafkaListenerContainerFactory);

    }

}
