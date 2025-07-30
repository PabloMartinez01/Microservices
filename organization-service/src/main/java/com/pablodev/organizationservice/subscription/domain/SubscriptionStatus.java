package com.pablodev.organizationservice.subscription.domain;

/*
    FUTURE: start day after today
    ACTIVE: start day before today and expiration date after today
    EXPIRED: start day before today and expiration date before today
    CANCELLED: cancelled flag
 */
public enum SubscriptionStatus {
    FUTURE, ACTIVE, EXPIRED, CANCELLED;


    public static SubscriptionStatus fromSubscriptionDates(
            SubscriptionDateRange dateRange
    ) {

        boolean startIsBefore = startDate.isBeforeNow();
        boolean expirationIsBefore = expirationDate.isBeforeNow();

        if (startIsBefore && expirationIsBefore) {
            return SubscriptionStatus.EXPIRED;
        }
        if (startIsBefore) {
            return SubscriptionStatus.ACTIVE;
        }

        return SubscriptionStatus.FUTURE;
    }

}


