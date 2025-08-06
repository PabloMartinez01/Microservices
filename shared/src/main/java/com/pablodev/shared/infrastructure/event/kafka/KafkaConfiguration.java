package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.domain.event.MockOnUserCreated;
import com.pablodev.shared.domain.event.MockUserCreatedDomainEvent;
import com.pablodev.shared.domain.event.Topic;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
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
    public void init() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        MethodKafkaListenerEndpoint<String, DomainEvent> endpoint = new MethodKafkaListenerEndpoint<>();

        DomainSubscriber subscriberAnnotation = subscriber.getClass().getAnnotation(DomainSubscriber.class);
        Class<? extends DomainEvent> eventClass = subscriberAnnotation.value();

        Topic topicAnnotation = eventClass.getAnnotation(Topic.class);
        String topic = topicAnnotation.value();

        endpoint.setId(UUID.randomUUID().toString());
        endpoint.setGroupId(kafkaProperties.getConsumer().getGroupId());
        endpoint.setAutoStartup(true);
        endpoint.setTopics();
        endpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory());
        endpoint.setBean(subscriber);
        endpoint.setMethod(subscriber.getClass().getMethod("on", MockUserCreatedDomainEvent.class));
        registry.registerListenerContainer(endpoint, kafkaListenerContainerFactory);

    }

}
