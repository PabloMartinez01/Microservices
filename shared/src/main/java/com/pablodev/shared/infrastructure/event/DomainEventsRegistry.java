package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainEventDestination;
import com.pablodev.shared.infrastructure.event.exceptions.DomainEventDestinationNotFoundException;
import com.pablodev.shared.infrastructure.event.exceptions.DomainEventNotFoundException;
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
        for (Class<? extends DomainEvent> eventClass : reflections.getSubTypesOf(DomainEvent.class)) {
            String destination = Optional.ofNullable(eventClass.getAnnotation(DomainEventDestination.class))
                    .map(DomainEventDestination::value)
                    .orElse(null);
            events.put(eventClass, destination);
        }
    }

    public String getEventNameOf(Class<? extends DomainEvent> eventClass) {

        if (!events.containsKey(eventClass)) {
            throw new DomainEventNotFoundException(eventClass.getName());
        }

        return Optional.ofNullable(events.get(eventClass))
                .orElseThrow(() -> new DomainEventDestinationNotFoundException(eventClass.getName()));
    }

}
