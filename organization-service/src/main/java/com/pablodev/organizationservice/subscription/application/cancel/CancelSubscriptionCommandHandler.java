package com.pablodev.organizationservice.subscription.application.cancel;

import com.pablodev.shared.domain.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelSubscriptionCommandHandler implements CommandHandler<CancelSubscriptionCommand> {

    private final SubscriptionCanceller canceller;

    @Override
    public void handle(CancelSubscriptionCommand command) {
        canceller.cancel(command.id());
    }
}
