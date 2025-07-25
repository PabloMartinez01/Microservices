package com.pablodev.shared.domain.command;

public class CommandHandlerExecutionException extends RuntimeException {
    public CommandHandlerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
