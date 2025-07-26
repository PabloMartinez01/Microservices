package com.pablodev.organizationservice.organization.application.delete;

import com.pablodev.shared.domain.command.Command;

public record DeleteOrganizationCommand(String id) implements Command {

}
