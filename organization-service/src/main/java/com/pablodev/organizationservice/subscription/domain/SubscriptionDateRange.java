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

    private static void ensureDateNotNull(LocalDate date, String message) {
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
