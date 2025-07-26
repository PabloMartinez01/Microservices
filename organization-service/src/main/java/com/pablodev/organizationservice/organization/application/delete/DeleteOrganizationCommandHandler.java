package com.pablodev.organizationservice.organization.application.delete;

import com.pablodev.shared.domain.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOrganizationCommandHandler implements CommandHandler<DeleteOrganizationCommand> {

    private final OrganizationDeleter organizationDeleter;

    @Override
    public void handle(DeleteOrganizationCommand command) {
        organizationDeleter.delete(command.id());
    }
}
