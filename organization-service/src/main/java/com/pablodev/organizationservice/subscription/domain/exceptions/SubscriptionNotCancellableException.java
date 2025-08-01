package com.pablodev.organizationservice.subscription.domain.exceptions;

public class SubscriptionNotCancellableException extends RuntimeException {

    public SubscriptionNotCancellableException(String message) {
        super(message);
    }
}
