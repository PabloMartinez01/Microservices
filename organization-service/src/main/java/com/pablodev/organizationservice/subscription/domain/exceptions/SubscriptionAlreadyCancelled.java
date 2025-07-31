package com.pablodev.organizationservice.subscription.domain.exceptions;

public class SubscriptionAlreadyCancelled extends RuntimeException {

    public SubscriptionAlreadyCancelled() {
        super("The subscription has been already cancelled");
    }
}
