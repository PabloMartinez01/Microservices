package com.pablodev.organizationservice.subscription.domain.exceptions;

import java.time.LocalDate;

public class SubscriptionDateRangeOverlapException extends RuntimeException {

    public SubscriptionDateRangeOverlapException(LocalDate startDate, LocalDate endDate) {
        super("Other subscriptions between " + startDate + " and " + endDate);
    }
}
