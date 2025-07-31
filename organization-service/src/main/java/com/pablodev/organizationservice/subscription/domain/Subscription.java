package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionAlreadyCancelled;
import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionAlreadyExpired;
import com.pablodev.shared.domain.AggregateRoot;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Subscription extends AggregateRoot {

    private final SubscriptionId id;
    private final OrganizationId organizationId;
    private final SubscriptionDateRange dateRange;
    private boolean cancelled;

    private Subscription(
            SubscriptionId id,
            OrganizationId organizationId,
            SubscriptionDateRange dateRange,
            boolean cancelled
    ) {
        this.id = id;
        this.organizationId = organizationId;
        this.dateRange = dateRange;
        this.cancelled = cancelled;
    }

    public Subscription(
            String id,
            String organizationId,
            LocalDate startDate,
            LocalDate expirationDate,
            boolean cancelled
    ) {
        this(
                new SubscriptionId(id),
                new OrganizationId(organizationId),
                new SubscriptionDateRange(startDate, expirationDate),
                cancelled
        );
    }

    public static Subscription create(
            String id,
            String organizationId,
            LocalDate startDate,
            LocalDate expirationDate
    ) {
        return new Subscription(
                new SubscriptionId(id),
                new OrganizationId(organizationId),
                SubscriptionDateRange.create(startDate, expirationDate),
                false);
    }

    public void cancel() {

        SubscriptionStatus status = getStatus();

        if (status == SubscriptionStatus.CANCELLED) {
            throw new SubscriptionAlreadyCancelled();
        }

        if (status == SubscriptionStatus.EXPIRED) {
            throw new SubscriptionAlreadyExpired();
        }

        cancelled = false;
    }

    public SubscriptionStatus getStatus() {

        if (cancelled) {
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

    public String getIdValue() {
        return id.getValue();
    }

    public String getOrganizationIdValue() {
        return organizationId.getValue();
    }

    public LocalDate getStartDate() {
        return dateRange.getStartDate();
    }

    public LocalDate getExpirationDate() {
        return dateRange.getExpirationDate();
    }


}
