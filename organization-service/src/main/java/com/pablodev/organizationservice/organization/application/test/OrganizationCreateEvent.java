package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainEventAnnotation;

@DomainEventAnnotation(name = "organization_service.organization.create")
public class OrganizationCreateEvent extends OrganizationDomainEvent {


}
