package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.core.ConsumerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final ApplicationContext applicationContext;
    private final ConsumerFactory<String, DomainEvent> consumerFactory;
    private final KafkaListenerEndpointRegistry registry;
    private final KafkaProperties kafkaProperties;


    @PostConstruct
    public void init() {

        MethodKafkaListenerEndpoint<String, DomainEvent> methodKafkaListenerEndpoint =
                new MethodKafkaListenerEndpoint<>();

        methodKafkaListenerEndpoint.setGroupId(kafkaProperties.getConsumer().getGroupId());
        methodKafkaListenerEndpoint.setId(UUID.randomUUID().toString());

        registry.registerListenerContainer();

    }

}
