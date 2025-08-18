package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainEvent;

public interface Subscriber {

    public void on(DomainEvent event);

}
