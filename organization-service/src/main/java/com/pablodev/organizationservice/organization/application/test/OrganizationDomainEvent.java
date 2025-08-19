package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainEventAnnotation;

@DomainEventAnnotation(name = "organization_service.organization.*")
public class OrganizationDomainEvent extends DomainEvent {


}
