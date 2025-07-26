package com.pablodev.organizationservice.organization.application.update;

import com.pablodev.shared.domain.command.Command;

public record UpdateOrganizationCommand(
        String id,
        String name,
        String type,
        String street,
        String city,
        String state,
        String country)
        implements Command {

}
