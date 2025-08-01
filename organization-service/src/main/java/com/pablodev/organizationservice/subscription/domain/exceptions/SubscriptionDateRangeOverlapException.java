package com.pablodev.organizationservice.subscription.domain.exceptions;

public class SubscriptionDateRangeOverlapException extends RuntimeException {

    public SubscriptionDateRangeOverlapException() {
        super("Other subscription active");
    }
}
