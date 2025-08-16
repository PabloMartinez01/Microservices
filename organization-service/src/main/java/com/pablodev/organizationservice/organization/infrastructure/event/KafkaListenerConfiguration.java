package com.pablodev.organizationservice.organization.infrastructure.event;

import com.pablodev.organizationservice.organization.application.user.CreateInternalUserOnUserCreated;
import com.pablodev.shared.domain.event.MockOnUserCreated;
import com.pablodev.shared.infrastructure.event.kafka.KafkaListenerEndpointCustomizer;
import com.pablodev.shared.infrastructure.event.kafka.KafkaListenerEndpointCustomizer.KafkaListenerEndpointConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaListenerConfiguration {


    // @formatter:off
    @Bean
    public KafkaListenerEndpointCustomizer kafkaListenerEndpointCustomizer(KafkaListenerEndpointConfigurer configurer) {
        return configurer.configureListeners(listeners -> listeners
                .listener(CreateInternalUserOnUserCreated.class)
                    .groupId("organization-service")
                    .concurrency(2)
                .listener(MockOnUserCreated.class))
                .build();
    }
    // @formatter:on
}
