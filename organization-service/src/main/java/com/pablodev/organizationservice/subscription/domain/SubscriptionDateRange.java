package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.organizationservice.subscription.domain.exceptions.InvalidSubscriptionDateRangeException;
import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionIllegalArgumentException;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class SubscriptionDateRange {

    private final LocalDate startDate;
    private final LocalDate expirationDate;

    public SubscriptionDateRange(LocalDate startDate, LocalDate expirationDate) {
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        ensureValidDateRange();
    }

    public static SubscriptionDateRange create(LocalDate startDate, LocalDate expirationDate) {
        ensureFutureDateRange(startDate, expirationDate);
        return new SubscriptionDateRange(startDate, expirationDate);
    }

    private static void ensureFutureDateRange(LocalDate startDate, LocalDate expirationDate) {
        if (startDate.isBefore(LocalDate.now()) || expirationDate.isBefore(LocalDate.now())) {
            throw new SubscriptionIllegalArgumentException("Date must be before or equal to date");
        }
    }

    private void ensureDateNotNull(LocalDate date, String message) {
        if (date == null) {
            throw new SubscriptionIllegalArgumentException(message);
        }
    }

    private void ensureValidDateRange() {

        ensureDateNotNull(startDate, "Start date cannot be null");
        ensureDateNotNull(expirationDate, "Expiration date cannot be null");

        if (startDate.isAfter(expirationDate)) {
            throw new InvalidSubscriptionDateRangeException(startDate, expirationDate);
        }
    }

    public boolean startedBeforeNow() {
        return startDate.isBefore(LocalDate.now());
    }

    public boolean expiredBeforeNow() {
        return expirationDate.isBefore(LocalDate.now());
    }


}
