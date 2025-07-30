package com.pablodev.organizationservice.subscription.domain.exceptions;

import com.pablodev.organizationservice.subscription.domain.SubscriptionExpirationDate;
import com.pablodev.organizationservice.subscription.domain.SubscriptionStartDate;

public class InvalidSubscriptionDateRangeException extends RuntimeException {

    public InvalidSubscriptionDateRangeException(
            SubscriptionStartDate startDate,
            SubscriptionExpirationDate expirationDate) {
        super("%sis after %s".formatted(startDate.getValue(), expirationDate.getValue()));
    }
}
