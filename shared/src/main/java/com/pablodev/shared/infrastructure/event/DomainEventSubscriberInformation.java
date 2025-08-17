package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import lombok.Getter;

@Getter
public class DomainEventSubscriberInformation {

    private final Class<?> subscriber;
    private final Class<? extends DomainEvent> events;

    public DomainEventSubscriberInformation(Class<?> subscriber, Class<? extends DomainEvent> events) {

        if (subscriber.getAnnotation(DomainSubscriber.class) == null) {
            throw new IllegalArgumentException("Subscriber must be annotated with @DomainSubscriber");
        }

        this.subscriber = subscriber;
        this.events = events;
    }

}
