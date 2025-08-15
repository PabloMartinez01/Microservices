package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KafkaConsumerRegistration {

    private final Class<?> subscriberClass;
    private final Class<? extends DomainEvent> eventClass;
    private String topic;
    private String groupId;
    private List<String> partitions;
    
}
