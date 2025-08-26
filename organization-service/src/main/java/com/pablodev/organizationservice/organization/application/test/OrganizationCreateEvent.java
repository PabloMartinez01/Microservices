package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainEventDestination;

@DomainEventDestination(value = "organization_service.organization.create")
public class OrganizationCreateEvent extends DomainEvent {


}
