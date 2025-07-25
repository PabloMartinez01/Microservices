package com.pablodev.shared.domain.command;

public interface CommandBus {
    public void dispatch(Command command);
}
