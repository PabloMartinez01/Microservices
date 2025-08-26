package com.pablodev.organizationservice.organization.application.test;

import com.pablodev.shared.domain.event.DomainSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OtherCreateSubscriber implements DomainSubscriber<OrganizationCreateEvent> {

    @Override
    public void on(OrganizationCreateEvent event) {
        log.info("OrganizationCreateEvent received");
    }
}
