package com.pablodev.shared.infrastructure.event.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class KafkaMessageHandlerMethodFactoryConfiguration {

    @Bean
    public DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory methodFactory = new DefaultMessageHandlerMethodFactory();
        methodFactory.setMessageConverter(new MappingJackson2MessageConverter());
        methodFactory.afterPropertiesSet();
        return methodFactory;
    }

}
