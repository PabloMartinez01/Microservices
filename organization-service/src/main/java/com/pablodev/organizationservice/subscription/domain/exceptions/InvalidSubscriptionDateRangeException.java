package com.pablodev.organizationservice.subscription.domain.exceptions;

import java.time.LocalDate;

public class InvalidSubscriptionDateRangeException extends RuntimeException {

    public InvalidSubscriptionDateRangeException(LocalDate startDate, LocalDate expirationDate) {
        super("%s is before %s".formatted(expirationDate, startDate));
    }
}
