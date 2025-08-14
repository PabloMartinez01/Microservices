package com.pablodev.shared.infrastructure.event.kafka;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.infrastructure.event.DomainEventSubscribersRegistry;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaDispatcher {

    private final DomainEventSubscribersRegistry subscribersRegistry;
    private final ApplicationContext applicationContext;

    public void dispatch(DomainEvent event)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<Class<?>> subscriberClasses = subscribersRegistry.getSubscribersForEvent(event.getClass());

        for (Class<?> subscriberClass : subscriberClasses) {
            Object subscriber = applicationContext.getBean(subscriberClass);
            Method onMethod = subscriberClass.getDeclaredMethod("on", subscriberClass);
            onMethod.invoke(subscriber, event);
        }

    }

}
