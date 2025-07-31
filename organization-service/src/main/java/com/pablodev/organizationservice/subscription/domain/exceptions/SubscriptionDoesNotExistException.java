package com.pablodev.organizationservice.subscription.domain.exceptions;

public class SubscriptionDoesNotExistException extends RuntimeException {

    public SubscriptionDoesNotExistException(String id) {
        super("Subscription with id %s does not exist".formatted(id));
    }
}
