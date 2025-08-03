package com.pablodev.organizationservice.subscription.application.create;

import com.pablodev.shared.domain.command.Command;

public record CreateSubscriptionCommand(
        String id,
        String organizationId)
        implements Command {

}
