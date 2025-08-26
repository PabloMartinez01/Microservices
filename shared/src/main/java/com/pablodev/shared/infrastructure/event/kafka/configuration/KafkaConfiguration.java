package com.pablodev.shared.infrastructure.event.kafka.configuration;

import com.pablodev.shared.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final ConsumerFactory<String, DomainEvent> consumerFactory;
    private final KafkaListenerEndpointRegistry registry;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DomainEvent> kafkaListenerContainer() {
        ConcurrentKafkaListenerContainerFactory<String, DomainEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate, (consumerRecord, _) ->
                        new TopicPartition("dlq-" + consumerRecord.topic(), consumerRecord.partition())),
                new FixedBackOff(5000L, 3)
        ));
        return factory;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory methodFactory = new DefaultMessageHandlerMethodFactory();
        methodFactory.setMessageConverter(new MappingJackson2MessageConverter());
        methodFactory.afterPropertiesSet();
        return methodFactory;
    }

    @Bean
    public KafkaListenerEndpointRegistrar kafkaListenerEndpointRegistrar() {
        KafkaListenerEndpointRegistrar kafkaListenerEndpointRegistrar = new KafkaListenerEndpointRegistrar();
        kafkaListenerEndpointRegistrar.setEndpointRegistry(registry);
        kafkaListenerEndpointRegistrar.setContainerFactory(kafkaListenerContainer());
        return kafkaListenerEndpointRegistrar;
    }

}
