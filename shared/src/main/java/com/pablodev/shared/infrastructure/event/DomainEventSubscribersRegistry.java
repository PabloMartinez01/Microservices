package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class DomainEventSubscribersRegistry {

    private final Map<Class<?>, DomainEventSubscriberInformation> subscribersInformation = new HashMap<>();

    @PostConstruct
    public void postConstruct() {

        Reflections reflections = new Reflections("com.pablodev");
        Set<Class<?>> subscribers = reflections.getTypesAnnotatedWith(DomainSubscriber.class);

        for (Class<?> subscriber : subscribers) {
            Class<? extends DomainEvent> event = subscriber.getAnnotation(DomainSubscriber.class).value();

            DomainEventSubscriberInformation subscription =
                    new DomainEventSubscriberInformation(subscriber, Collections.singletonList(event));

            this.subscribersInformation.put(subscriber, subscription);
        }

    }

    public List<DomainEventSubscriberInformation> getSubscribersInformation() {
        return subscribersInformation.values().stream().toList();
    }


}
