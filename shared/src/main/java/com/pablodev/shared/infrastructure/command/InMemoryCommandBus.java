package com.pablodev.shared.infrastructure.command;

import com.pablodev.shared.domain.command.Command;
import com.pablodev.shared.domain.command.CommandBus;
import com.pablodev.shared.domain.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InMemoryCommandBus implements CommandBus {

    private final CommandRegistry commandRegistry;

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void dispatch(Command command) {
        CommandHandler commandHandler = commandRegistry.getCommandHandler(command.getClass());
        commandHandler.handle(command);
    }

}
