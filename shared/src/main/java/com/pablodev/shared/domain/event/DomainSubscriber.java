package com.pablodev.shared.domain.event;

public interface DomainSubscriber<T extends DomainEvent> {

    public void on(T event);
}
