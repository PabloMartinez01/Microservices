package com.pablodev.shared.infrastructure.event.kafka.registration;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.DomainEventsRegistry;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerTopicResolver {

    private final DomainEventsRegistry eventsRegistry;

    public Pattern resolve(Class<? extends DomainEvent> eventClass) {
        return Pattern.compile(eventsRegistry.getEventNameOf(eventClass));
    }

}
