package com.pablodev.organizationservice.subscription.domain;

/*
    FUTURE: start day after today
    ACTIVE: start day before today and expiration date after today
    EXPIRED: start day before today and expiration date before today
    CANCELLED: cancelled flag
 */
public enum SubscriptionStatus {
    ACTIVE, EXPIRED, CANCELLED;
}


