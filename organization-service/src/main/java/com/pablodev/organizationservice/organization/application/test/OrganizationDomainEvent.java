package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.AbstractDomainEvent;
import com.pablodev.shared.domain.event.DomainEvent;

@DomainEvent(name = "organization_service.organization.*")
public class OrganizationDomainEvent extends AbstractDomainEvent {


}
