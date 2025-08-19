package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainEvent;

@DomainEvent(name = "organization_service.organization.delete")
public class OrganizationDeleteEvent extends OrganizationDomainEvent {


}
