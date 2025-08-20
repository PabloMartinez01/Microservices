package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainEventDestination;
import java.util.Optional;
import lombok.Getter;

@Getter
public class DomainEventSubscriberInformation {

    private final Class<?> subscriberClass;
    private final Class<? extends DomainEvent> eventClass;

    public DomainEventSubscriberInformation(Class<?> subscriberClass,
            Class<? extends DomainEvent> eventClass) {

        ensureSubscriberHasAnnotation(subscriberClass);
        ensureEventHasAnnotation(eventClass);

        this.subscriberClass = subscriberClass;
        this.eventClass = eventClass;
    }

    private static void ensureSubscriberHasAnnotation(Class<?> subscriberClass) {
        Optional.ofNullable(subscriberClass.getAnnotation(DomainSubscriber.class))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Subscriber must be annotated with @DomainSubscriber"));
    }

    private static void ensureEventHasAnnotation(Class<? extends DomainEvent> eventClass) {
        Optional.ofNullable(eventClass.getAnnotation(DomainEventDestination.class))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Event must be annotated with @DomainEventAnnotation"));
    }

}
