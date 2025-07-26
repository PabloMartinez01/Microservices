package com.pablodev.organizationservice.organization.application.update;

import com.pablodev.shared.domain.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateOrganizationCommandHandler implements CommandHandler<UpdateOrganizationCommand> {

    private final OrganizationUpdater organizationUpdater;

    @Override
    public void handle(UpdateOrganizationCommand command) {
        organizationUpdater.update(
                command.id(),
                command.name(),
                command.type(),
                command.street(),
                command.city(),
                command.state(),
                command.country()
        );
    }
    
}
