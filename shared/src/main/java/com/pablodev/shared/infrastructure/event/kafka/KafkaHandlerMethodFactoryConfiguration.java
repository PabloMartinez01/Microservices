package com.pablodev.shared.infrastructure.event.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class KafkaHandlerMethodFactoryConfiguration {

    @Bean
    public DefaultMessageHandlerMethodFactory kafkaMessageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(new MappingJackson2MessageConverter());
        messageHandlerMethodFactory.afterPropertiesSet();
        return messageHandlerMethodFactory;
    }

}
