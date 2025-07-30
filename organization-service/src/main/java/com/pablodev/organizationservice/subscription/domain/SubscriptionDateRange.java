package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.organizationservice.subscription.domain.exceptions.InvalidSubscriptionDateRangeException;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class SubscriptionDateRange {

    private final SubscriptionStartDate startDate;
    private final SubscriptionExpirationDate expirationDate;

    private SubscriptionDateRange(
            SubscriptionStartDate startDate,
            SubscriptionExpirationDate expirationDate) {
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        ensureValidDateRange();
    }

    public SubscriptionDateRange(LocalDate startDate, LocalDate expirationDate) {
        this(new SubscriptionStartDate(startDate), new SubscriptionExpirationDate(expirationDate));
    }

    private void ensureValidDateRange() {
        if (startDate.getValue().isAfter(expirationDate.getValue())) {
            throw new InvalidSubscriptionDateRangeException(startDate, expirationDate);
        }
    }

    public boolean startedBeforeNow() {
        return startDate.getValue().isBefore(LocalDate.now());
    }

    public boolean expiredBeforeNow() {
        return expirationDate.getValue().isBefore(LocalDate.now());
    }


}
