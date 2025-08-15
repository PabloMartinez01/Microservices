package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaListenerEndpointRegistry registry;
    private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DomainEvent>> kafkaListenerContainerFactory;

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
        kafkaListenerEndpointRegistrar.setContainerFactory(kafkaListenerContainerFactory);
        return kafkaListenerEndpointRegistrar;
    }

}
