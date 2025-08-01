package com.pablodev.organizationservice.subscription.domain;

import com.pablodev.organizationservice.organization.domain.OrganizationId;
import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionNotCancellableException;
import com.pablodev.shared.domain.AggregateRoot;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Subscription extends AggregateRoot {

    private static final Integer SUBSCRIPTION_DURATION_DAYS = 30;

    private final SubscriptionId id;
    private final OrganizationId organizationId;
    private final SubscriptionDateRange dateRange;
    private final SubscriptionStatus status;
    private boolean cancelled;

    private Subscription(SubscriptionId id, OrganizationId organizationId, SubscriptionDateRange dateRange,
            boolean cancelled) {
        this.id = id;
        this.organizationId = organizationId;
        this.dateRange = dateRange;
        this.cancelled = cancelled;
        this.status = calculateStatus();
    }


    public static Subscription fromData(
            String subscriptionId,
            String organizationId,
            LocalDate startDate,
            LocalDate expirationDate,
            boolean cancelled
    ) {

        SubscriptionDateRange dateRange = SubscriptionDateRange.fromData(startDate, expirationDate);

        return new Subscription(
                new SubscriptionId(subscriptionId),
                new OrganizationId(organizationId),
                dateRange,
                cancelled);
    }

    public static Subscription create(String subscriptionId, String organizationId) {

        SubscriptionDateRange dateRange = SubscriptionDateRange.create(SUBSCRIPTION_DURATION_DAYS);

        return new Subscription(
                new SubscriptionId(subscriptionId),
                new OrganizationId(organizationId),
                dateRange,
                false
        );
    }

    public void cancel() {

        if (status == SubscriptionStatus.CANCELLED) {
            throw new SubscriptionNotCancellableException("The subscription is already cancelled");
        }

        if (status == SubscriptionStatus.EXPIRED) {
            throw new SubscriptionNotCancellableException("The subscription is expired");
        }

        cancelled = true;
    }


    private SubscriptionStatus calculateStatus() {

        if (cancelled) {
            return SubscriptionStatus.CANCELLED;
        }

        if (dateRange.expiredBeforeNow()) {
            return SubscriptionStatus.EXPIRED;
        }

        return SubscriptionStatus.ACTIVE;
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
