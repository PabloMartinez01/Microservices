package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import lombok.Getter;

@Getter
public class DomainSubscriberInformation {

    private final Class<? extends DomainSubscriber<?>> subscriberClass;
    private final Class<? extends DomainEvent> eventClass;

    public DomainSubscriberInformation(
            Class<? extends DomainSubscriber<?>> subscriberClass,
            Class<? extends DomainEvent> eventClass
    ) {
        this.subscriberClass = subscriberClass;
        this.eventClass = eventClass;
    }


}
