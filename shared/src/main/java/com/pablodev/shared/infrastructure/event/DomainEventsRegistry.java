package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class DomainEventsRegistry {

    private final Map<Class<? extends DomainEvent>, String> events = new HashMap<>();
    private final Map<String, Class<? extends DomainEvent>> eventsByName = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        Reflections reflections = new Reflections("com.pablodev");
        reflections.getSubTypesOf(DomainEvent.class).forEach(this::addDomainEvent);
    }

    public String getEventNameOf(Class<? extends DomainEvent> event) {
        return Optional.ofNullable(events.get(event)).
                orElseThrow(() -> new IllegalArgumentException("No event found for " + event.getName()));
    }

    public Class<? extends DomainEvent> getDomainEvent(String eventName) {
        return eventsByName.get(eventName);
    }

    private void addDomainEvent(Class<? extends DomainEvent> event) {
        try {
            String eventName = event.getConstructor().newInstance().getEventName();
            events.put(event, eventName);
            eventsByName.put(eventName, event);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

}
