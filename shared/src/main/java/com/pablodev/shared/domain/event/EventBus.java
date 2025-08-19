package com.pablodev.shared.domain.event;

import java.util.List;

public interface EventBus {

    void publish(List<AbstractDomainEvent> events);

}
