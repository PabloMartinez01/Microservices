package com.pablodev.organizationservice.subscription.application.create;

import com.pablodev.shared.domain.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateSubscriptionCommandHandler implements CommandHandler<CreateSubscriptionCommand> {

    private final SubscriptionCreator creator;

    @Override
    public void handle(CreateSubscriptionCommand command) {
        creator.create(command);
    }
}
