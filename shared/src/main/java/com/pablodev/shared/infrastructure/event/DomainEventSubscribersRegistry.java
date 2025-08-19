package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.AbstractDomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class DomainEventSubscribersRegistry {

    private final Map<Class<?>, DomainEventSubscriberInformation> subscribersByClass = new HashMap<>();
    private final Map<Class<? extends AbstractDomainEvent>, List<DomainEventSubscriberInformation>> subscribersByEvent = new HashMap<>();

    @PostConstruct
    public void postConstruct() {

        Reflections reflections = new Reflections("com.pablodev");
        Set<Class<?>> subscribers = reflections.getTypesAnnotatedWith(DomainSubscriber.class);

        for (Class<?> subscriber : subscribers) {
            Class<? extends AbstractDomainEvent> event = subscriber.getAnnotation(
                    DomainSubscriber.class).value();

            DomainEventSubscriberInformation subscription =
                    new DomainEventSubscriberInformation(subscriber, event);

            addIndexedEventSubscriber(event, subscription);
            this.subscribersByClass.put(subscriber, subscription);
        }


    }

    private void addIndexedEventSubscriber(Class<? extends AbstractDomainEvent> event,
            DomainEventSubscriberInformation subscription) {
        List<DomainEventSubscriberInformation> list =
                subscribersByEvent.computeIfAbsent(event, _ -> new ArrayList<>());
        if (!list.contains(subscription)) {
            list.add(subscription);
        }
    }


    public List<Class<?>> getSubscribersForEvent(Class<? extends AbstractDomainEvent> event) {
        return subscribersByEvent.get(event).stream()
                .map(DomainEventSubscriberInformation::getSubscriberClass)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<DomainEventSubscriberInformation> getSubscribers() {
        return subscribersByClass.values().stream().toList();
    }


}
