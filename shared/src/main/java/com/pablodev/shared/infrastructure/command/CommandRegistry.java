package com.pablodev.shared.infrastructure.command;

import com.pablodev.shared.domain.command.Command;
import com.pablodev.shared.domain.command.CommandHandler;
import com.pablodev.shared.domain.command.CommandNotRegisteredException;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CommandRegistry {

    private final Map<Class<? extends Command>, CommandHandler<? extends Command>> commandHandlers;
    private final ApplicationContext applicationContext;

    public CommandRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.commandHandlers = new HashMap<>();
        constructCommandHandlersMap();
    }


    public CommandHandler<?> getCommandHandler(Class<? extends Command> commandClass) {
        return Optional.ofNullable(commandHandlers.get(commandClass))
                .orElseThrow(() -> new CommandNotRegisteredException(commandClass));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void constructCommandHandlersMap() {
        Reflections reflections = new Reflections("com.pablodev");
        for (Class<? extends CommandHandler> commandHandlerClass : reflections.getSubTypesOf(CommandHandler.class)) {
            CommandHandler<?> commandHandler = applicationContext.getBean(commandHandlerClass);
            ParameterizedType parameterizedType = (ParameterizedType) commandHandlerClass.getGenericInterfaces()[0];
            Class<? extends Command> commandClass = (Class<? extends Command>) parameterizedType.getActualTypeArguments()[0];
            commandHandlers.put(commandClass, commandHandler);
        }
    }


}
