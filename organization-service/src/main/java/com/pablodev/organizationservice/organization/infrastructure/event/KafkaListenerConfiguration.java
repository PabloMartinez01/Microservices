package com.pablodev.organizationservice.organization.infrastructure.event;

import com.pablodev.organizationservice.organization.application.user.CreateInternalUserOnUserCreated;
import com.pablodev.shared.domain.event.MockOnUserCreated;
import com.pablodev.shared.infrastructure.event.kafka.KafkaListenerEndpointCustomizer;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerConfiguration extends KafkaListenerEndpointCustomizer {


    // @formatter:off
    @Override
    public void customize(KafkaListenerEndpointConfigurer configurer) {

        configurer.configureListeners(listeners -> listeners
                .listener(CreateInternalUserOnUserCreated.class)
                    .groupId("organization-service")
                    .concurrency(2)
                .listener(MockOnUserCreated.class)
        );


    }
    // @formatter:on
}
