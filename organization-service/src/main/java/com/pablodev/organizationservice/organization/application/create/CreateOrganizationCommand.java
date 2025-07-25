package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.shared.domain.command.Command;

public record CreateOrganizationCommand(
        String id,
        String name,
        String type,
        String street,
        String city,
        String state,
        String country)
        implements Command {

}