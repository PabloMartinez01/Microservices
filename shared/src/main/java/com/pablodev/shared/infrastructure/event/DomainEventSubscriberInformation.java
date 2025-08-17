package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import lombok.Getter;

@Getter
public class DomainEventSubscriberInformation {

    private final Class<?> subscriberClass;
    private final Class<? extends DomainEvent> eventClass;

    public DomainEventSubscriberInformation(Class<?> subscriberClass,
            Class<? extends DomainEvent> eventClass) {

        if (subscriberClass.getAnnotation(DomainSubscriber.class) == null) {
            throw new IllegalArgumentException("Subscriber must be annotated with @DomainSubscriber");
        }

        this.subscriberClass = subscriberClass;
        this.eventClass = eventClass;
    }

}
