package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.MockOnUserCreated;
import com.pablodev.shared.domain.event.MockUserCreatedDomainEvent;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final ApplicationContext applicationContext;
    private final ConsumerFactory<String, DomainEvent> consumerFactory;
    private final KafkaListenerEndpointRegistry registry;
    private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DomainEvent>> kafkaListenerContainerFactory;
    private final KafkaProperties kafkaProperties;
    private final MockOnUserCreated subscriber;


    @PostConstruct
    public void init() throws NoSuchMethodException {

        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();

        MockUserCreatedDomainEvent event = new MockUserCreatedDomainEvent("example@email.com");

        endpoint.setId(UUID.randomUUID().toString());
        endpoint.setGroupId(kafkaProperties.getConsumer().getGroupId());
        endpoint.setAutoStartup(true);
        endpoint.setTopics(event.getEventName());
        endpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory());
        endpoint.setBean(subscriber);
        endpoint.setMethod(subscriber.getClass().getMethod("on", MockUserCreatedDomainEvent.class));
        registry.registerListenerContainer(endpoint, kafkaListenerContainerFactory);

    }

}
