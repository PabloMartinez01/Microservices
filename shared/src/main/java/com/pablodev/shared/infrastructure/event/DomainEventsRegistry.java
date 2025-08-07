package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class DomainEventsRegistry {

    private final Map<Class<? extends DomainEvent>, String> events = new HashMap<>();

    @PostConstruct
    public void postConstruct()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Reflections reflections = new Reflections("com.pablodev");
        Set<Class<? extends DomainEvent>> subtypes = reflections.getSubTypesOf(DomainEvent.class);

        for (Class<? extends DomainEvent> subtype : subtypes) {
            String eventName = subtype.getConstructor().newInstance().getEventName();
            events.put(subtype, eventName);
        }

    }

    public String getEventNameOf(Class<? extends DomainEvent> event) {
        return Optional.ofNullable(events.get(event)).
                orElseThrow(
                        () -> new IllegalArgumentException("No event found for event " + event.getName()));
    }

}
