package com.pablodev.organizationservice.subscription.domain.exceptions;

public class SubscriptionAlreadyExpired extends RuntimeException {

    public SubscriptionAlreadyExpired() {
        super("Subscription has been already expired");
    }
}
