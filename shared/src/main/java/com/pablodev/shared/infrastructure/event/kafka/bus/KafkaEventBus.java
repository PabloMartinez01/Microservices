package com.pablodev.shared.infrastructure.event.kafka.bus;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.EventBus;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventBus implements EventBus {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final DomainEventsRegistry registry;

    @Override
    public void publish(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            kafkaTemplate.send(registry.getEventNameOf(event.getClass()), event);
        }
    }


}
