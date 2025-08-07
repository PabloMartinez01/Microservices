package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import java.util.List;
import lombok.Getter;

@Getter
public class DomainEventSubscriberInformation {

    private final Class<?> subscriber;
    private final List<Class<? extends DomainEvent>> events;

    public DomainEventSubscriberInformation(Class<?> subscriber, List<Class<? extends DomainEvent>> events) {

        if (subscriber.getAnnotation(DomainSubscriber.class) == null) {
            throw new IllegalArgumentException("Subscriber must be annotated with @DomainSubscriber");
        }

        this.subscriber = subscriber;
        this.events = events;
    }

    public String getSubscriberName() {
        String[] split = subscriber.getName().split("\\.");
        return split[split.length - 1];
    }


}
