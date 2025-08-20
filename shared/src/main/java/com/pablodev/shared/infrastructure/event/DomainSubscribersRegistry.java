package com.pablodev.shared.infrastructure.event;

import com.pablodev.shared.domain.event.DomainEvent;
import com.pablodev.shared.domain.event.DomainSubscriber;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class DomainSubscribersRegistry {

    private final Map<Class<? extends DomainSubscriber<?>>, DomainSubscriberInformation> subscribersByClass = new HashMap<>();

    @PostConstruct
    @SuppressWarnings({"unchecked"})
    public void postConstruct() {
        for (Class<? extends DomainSubscriber<?>> subscriberClass : getDomainSubscribers()) {
            for (Type type : subscriberClass.getGenericInterfaces()) {
                if (isDomainSubscriberInterface(type)) {
                    Type eventType = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (eventType instanceof Class<?>) {
                        DomainSubscriberInformation subscription = new DomainSubscriberInformation(
                                subscriberClass, (Class<? extends DomainEvent>) eventType);
                        subscribersByClass.put(subscriberClass, subscription);
                    }
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    private Set<Class<? extends DomainSubscriber<?>>> getDomainSubscribers() {
        Reflections reflections = new Reflections("com.pablodev");
        return reflections.getSubTypesOf((Class<DomainSubscriber<?>>) (Class<?>) DomainSubscriber.class);
    }

    private boolean isDomainSubscriberInterface(Type type) {
        return type instanceof ParameterizedType parameterizedType
                && parameterizedType.getRawType() == DomainSubscriber.class;
    }


    public List<DomainSubscriberInformation> getSubscribers() {
        return subscribersByClass.values().stream().toList();
    }


}
