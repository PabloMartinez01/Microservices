package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.shared.domain.AggregateRoot;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class Subscription extends AggregateRoot {

    private final SubscriptionId id;
    private final SubscriptionStartDate startDate;
    private final SubscriptionExpirationDate expirationDate;
    private final SubscriptionStatus status;

    public Subscription(
            SubscriptionId id,
            SubscriptionStartDate startDate,
            SubscriptionExpirationDate expirationDate,
            SubscriptionStatus status) {

        this.id = id;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    public static Subscription create(String id, LocalDate startDate, LocalDate expirationDate,
            boolean status) {
        return new Subscription(
                new SubscriptionId(id),
                new SubscriptionStartDate(startDate),
                new SubscriptionExpirationDate(expirationDate),
                new SubscriptionStatus(status)
        );
    }

}
