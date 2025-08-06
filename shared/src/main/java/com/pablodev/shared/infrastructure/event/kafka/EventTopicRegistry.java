package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.Topic;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class EventRegistry {

    private final Map<Class<? extends DomainEvent>, String> events = new HashMap<>();

    @PostConstruct
    public void postConstruct() {

        Reflections reflections = new Reflections("com.pablodev");
        Set<Class<? extends DomainEvent>> subtypes = reflections.getSubTypesOf(DomainEvent.class);

        for (Class<? extends DomainEvent> subtype : subtypes) {

            Topic topic = Optional.ofNullable(subtype.getAnnotation(Topic.class))
                    .orElseThrow(() -> new RuntimeException(
                            "Domain Events must have a topic, try to annotated with @Topic"));

            events.put(subtype, topic.value());
        }

    }

    public String getTopicFor(Class<? extends DomainEvent> eventClass) {
        return Optional.ofNullable(events.get(eventClass))
                .orElseThrow(() -> new IllegalArgumentException("No topic registered for " + eventClass));
    }

}
