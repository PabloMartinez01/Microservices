package com.pablodev.organizationservice.subscription.application.create;

import com.pablodev.shared.domain.command.Command;
import java.time.LocalDate;

public record CreateSubscriptionCommand(
        String id,
        String organizationId,
        LocalDate startDate,
        LocalDate expirationDate)
        implements Command {

}
