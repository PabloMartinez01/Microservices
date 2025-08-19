package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainEventAnnotation;

@DomainEventAnnotation(name = "organization_service.organization.delete")
public class OrganizationDeleteEvent extends OrganizationDomainEvent {


}
