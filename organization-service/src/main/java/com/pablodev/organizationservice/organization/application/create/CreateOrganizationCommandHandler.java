package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.shared.domain.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrganizationCommandHandler implements CommandHandler<CreateOrganizationCommand> {

    private final OrganizationCreator organizationCreator;

    @Override
    public void handle(CreateOrganizationCommand command) {
        organizationCreator.create(
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
