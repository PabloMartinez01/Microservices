package com.pablodev.shared.infrastructure.event.kafka.registration;

import com.pablodev.shared.domain.Utils;
import com.pablodev.shared.domain.event.DomainSubscriber;
import com.pablodev.shared.infrastructure.event.kafka.configuration.KafkaConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerGroupIdResolver {

    private final KafkaConfigurationProperties kafkaProperties;

    public String resolve(Class<? extends DomainSubscriber<?>> subscriberClass) {
        String groupId = kafkaProperties.getConsumerPrefix() + "." + subscriberClass.getSimpleName();
        return Utils.toSnakeCase(groupId);
    }

}
