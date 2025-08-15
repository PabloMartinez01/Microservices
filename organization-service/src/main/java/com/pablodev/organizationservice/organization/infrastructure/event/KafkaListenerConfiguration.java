package com.pablodev.organizationservice.organization.infrastructure.event;

import com.pablodev.organizationservice.organization.application.user.CreateInternalUserOnUserCreated;
import com.pablodev.shared.infrastructure.event.kafka.KafkaListenerEndpointCustomizer;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerConfiguration extends KafkaListenerEndpointCustomizer {

    @Override
    public void customize() {

        listener(CreateInternalUserOnUserCreated.class)
                .groupId("create-internal-user-on-user-created");

    }


}
