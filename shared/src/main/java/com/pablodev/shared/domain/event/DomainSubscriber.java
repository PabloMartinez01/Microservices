package com.pablodev.shared.domain.event;

public interface DomainSubscriber<T extends DomainEvent> {

    void on(T event);
}
