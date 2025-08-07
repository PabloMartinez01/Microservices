package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class DomainEventSubscriberInformation {

    private final Class<?> subscriber;
    private final List<Class<? extends DomainEvent>> events;

    public DomainEventSubscriberInformation(Class<?> subscriber, List<Class<? extends DomainEvent>> events) {
        Optional.ofNullable(subscriber.getAnnotation(DomainSubscriber.class))
                .orElseThrow(() -> new IllegalArgumentException(
                        "The subscriber must have a @DomainSubscriber annotation"));

        this.subscriber = subscriber;
        this.events = events;
    }


}
