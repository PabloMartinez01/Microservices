package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainEventDestination;
import com.pablodev.shared.domain.event.DomainSubscriber;
import java.util.Optional;
import lombok.Getter;

@Getter
public class DomainSubscriberInformation {

    private final Class<? extends DomainSubscriber<?>> subscriberClass;
    private final Class<? extends DomainEvent> eventClass;

    public DomainSubscriberInformation(
            Class<? extends DomainSubscriber<?>> subscriberClass,
            Class<? extends DomainEvent> eventClass
    ) {
        ensureEventHasAnnotation(eventClass);
        this.subscriberClass = subscriberClass;
        this.eventClass = eventClass;
    }


    private static void ensureEventHasAnnotation(Class<? extends DomainEvent> eventClass) {
        Optional.ofNullable(eventClass.getAnnotation(DomainEventDestination.class))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Event must be annotated with @DomainEventAnnotation"));
    }

}
