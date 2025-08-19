package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.AbstractDomainEvent;
import com.pablodev.shared.domain.event.EventBus;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventBus implements EventBus {

    private final KafkaTemplate<String, AbstractDomainEvent> kafkaTemplate;
    private final DomainEventsRegistry registry;

    @Override
    public void publish(List<AbstractDomainEvent> events) {
        for (AbstractDomainEvent event : events) {
            kafkaTemplate.send(event.getEventName(), event);
        }
    }


}
