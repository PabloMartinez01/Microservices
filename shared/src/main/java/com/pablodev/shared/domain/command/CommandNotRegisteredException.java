package com.pablodev.shared.domain.command;

public class CommandNotRegisteredException extends RuntimeException {

    public CommandNotRegisteredException(Class<? extends Command> commandClass) {
        super("Command %s is not registered".formatted(commandClass.getSimpleName()));
    }
}
