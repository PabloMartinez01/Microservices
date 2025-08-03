package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionIllegalArgumentException;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class SubscriptionDateRange {

    private final LocalDate startDate;
    private final LocalDate expirationDate;

    private SubscriptionDateRange(LocalDate startDate, LocalDate expirationDate) {
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public static SubscriptionDateRange create(Integer durationInDays) {
        ensureValidDuration(durationInDays);
        LocalDate startDate = LocalDate.now();
        LocalDate expirationDate = startDate.plusDays(durationInDays);
        return new SubscriptionDateRange(startDate, expirationDate);
    }

    public static SubscriptionDateRange fromData(LocalDate startDate, LocalDate expirationDate) {
        return new SubscriptionDateRange(startDate, expirationDate);
    }

    private static void ensureValidDuration(Integer durationInDays) {
        if (durationInDays == null || durationInDays < 1) {
            throw new SubscriptionIllegalArgumentException("Duration must be greater than 0");
        }
    }

    public boolean expiredBeforeNow() {
        return expirationDate.isBefore(LocalDate.now());
    }


}
