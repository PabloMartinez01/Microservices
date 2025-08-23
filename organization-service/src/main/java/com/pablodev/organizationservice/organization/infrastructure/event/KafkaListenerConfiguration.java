package com.pablodev.organizationservice.organization.infrastructure.event;

import com.pablodev.organizationservice.organization.application.test.OrganizationSubscriber;
import com.pablodev.shared.infrastructure.event.kafka.customization.KafkaListenerEndpointCustomizer;
import com.pablodev.shared.infrastructure.event.kafka.customization.KafkaListenerEndpointCustomizerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaListenerConfiguration {

    // @formatter:off
    @Bean
    public KafkaListenerEndpointCustomizer kafkaListenerEndpointCustomizer(KafkaListenerEndpointCustomizerBuilder builder) {
        return builder.configureListeners(listeners -> listeners
                .listener(OrganizationSubscriber.class)
                        .groupId("organization_service.custom_group"))
                .build();
    }
    // @formatter:on
}
