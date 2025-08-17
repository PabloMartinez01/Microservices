package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.exceptions.DomainEventNotFoundException;
import com.pablodev.shared.infrastructure.event.exceptions.DomainEventNotRegistrableException;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class DomainEventsRegistry {

    private final Map<Class<? extends DomainEvent>, String> events = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        Reflections reflections = new Reflections("com.pablodev");
        reflections.getSubTypesOf(DomainEvent.class).forEach(this::addDomainEvent);
    }

    public String getEventNameOf(Class<? extends DomainEvent> event) {
        return Optional.ofNullable(events.get(event))
                .orElseThrow(() -> new DomainEventNotFoundException(event.getName()));
    }
    
    private void addDomainEvent(Class<? extends DomainEvent> event) {
        try {
            String eventName = event.getConstructor().newInstance().getEventName();
            events.put(event, eventName);
        } catch (Exception e) {
            throw new DomainEventNotRegistrableException(e.getMessage(), e.getCause());
        }
    }

}
