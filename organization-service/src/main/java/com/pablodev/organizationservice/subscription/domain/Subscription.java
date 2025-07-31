package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.AggregateRoot;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
public class Subscription extends AggregateRoot {

    private final SubscriptionId id;
    private final SubscriptionDateRange dateRange;
    private Boolean isCancelled;

    public Subscription(SubscriptionId id, SubscriptionDateRange dateRange, Boolean isCancelled) {
        this.id = id;
        this.dateRange = dateRange;
        this.isCancelled = isCancelled;
    }

    public Subscription(String id, LocalDate startDate, LocalDate expirationDate,
            Boolean isCancelled) {
        this(
                new SubscriptionId(id),
                new SubscriptionDateRange(startDate, expirationDate),
                isCancelled
        );
    }

    public static Subscription create(String id, LocalDate startDate, LocalDate expirationDate) {
        return new Subscription(id, startDate, expirationDate, false);
    }

    public void cancel() {
        isCancelled = false;
    }

    public SubscriptionStatus getStatus() {

        if (Boolean.TRUE.equals(isCancelled)) {
            return SubscriptionStatus.CANCELLED;
        }
        if (dateRange.startedBeforeNow() && dateRange.expiredBeforeNow()) {
            return SubscriptionStatus.EXPIRED;
        }

        if (dateRange.startedBeforeNow()) {
            return SubscriptionStatus.ACTIVE;
        }

        return SubscriptionStatus.FUTURE;

    }


}
