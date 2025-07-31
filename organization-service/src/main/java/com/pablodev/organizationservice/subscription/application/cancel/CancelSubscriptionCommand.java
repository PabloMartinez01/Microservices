package com.pablodev.organizationservice.subscription.application.cancel;

import com.pablodev.shared.domain.command.Command;

public record CancelSubscriptionCommand(String id) implements Command {

}
