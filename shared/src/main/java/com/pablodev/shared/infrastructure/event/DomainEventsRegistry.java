package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.AbstractDomainEvent;
import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.exceptions.DomainEventNotFoundException;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class DomainEventsRegistry {

    private final Map<Class<? extends AbstractDomainEvent>, String> events = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        Reflections reflections = new Reflections("com.pablodev");

        reflections.getSubTypesOf(AbstractDomainEvent.class).forEach(eventClass -> {

            String eventName = Optional.ofNullable(eventClass.getAnnotation(DomainEvent.class))
                    .map(DomainEvent::name)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Domain event should be annotated with @DomainEvent"));

            events.put(eventClass, eventName);

        });
    }

    public String getEventNameOf(Class<? extends AbstractDomainEvent> event) {
        return Optional.ofNullable(events.get(event))
                .orElseThrow(() -> new DomainEventNotFoundException(event.getName()));
    }

}
