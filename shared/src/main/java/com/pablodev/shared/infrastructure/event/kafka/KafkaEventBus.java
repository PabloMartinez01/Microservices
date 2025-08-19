package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.EventBus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventBus implements EventBus {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Override
    public void publish(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            kafkaTemplate.send(event.getEventName(), event);
        }
    }


}
