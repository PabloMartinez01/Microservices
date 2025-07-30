package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.AggregateRoot;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
public class Subscription extends AggregateRoot {

    private final SubscriptionId id;
    private final SubscriptionDateRange dateRange;
    private SubscriptionCancelled isCancelled;

    public Subscription(
            SubscriptionId id,
            SubscriptionDateRange dateRange,
            SubscriptionCancelled isCancelled
    ) {
        this.id = id;
        this.dateRange = dateRange;
        this.isCancelled = isCancelled;
    }

    public Subscription(
            String id,
            LocalDate startDate,
            LocalDate expirationDate,
            boolean isCancelled
    ) {
        this(
                new SubscriptionId(id),
                new SubscriptionDateRange(startDate, expirationDate),
                new SubscriptionCancelled(isCancelled)
        );
    }

    public static Subscription create(String id, LocalDate startDate, LocalDate expirationDate) {
        return new Subscription(id, startDate, expirationDate, false);
    }

    public void cancel() {
        isCancelled = new SubscriptionCancelled(true);
    }

    public SubscriptionStatus getStatus() {

        if (isCancelled.getValue()) {
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
