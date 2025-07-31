package com.pablodev.organizationservice.subscription.application.find_by_organization;

import com.pablodev.organizationservice.organization.application.find.OrganizationFinder;
import com.pablodev.organizationservice.subscription.application.SubscriptionResponse;
import com.pablodev.organizationservice.subscription.application.SubscriptionsResponse;
import com.pablodev.organizationservice.subscription.domain.SubscriptionRepository;
import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.Order;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionFinderByOrganization {

    private final SubscriptionRepository subscriptionRepository;
    private final OrganizationFinder organizationFinder;

    public SubscriptionsResponse findByOrganization(String organizationId) {

        ensureOrganizationExists(organizationId);

        Criteria byOrganizationId = Criteria.of(
                Order.unordered(),
                Filter.equals("organizationId", organizationId)
        );

        List<SubscriptionResponse> responses = subscriptionRepository.search(byOrganizationId)
                .stream()
                .map(SubscriptionResponse::fromAggregate)
                .toList();

        return new SubscriptionsResponse(responses);
    }

    private void ensureOrganizationExists(String id) {
        organizationFinder.find(id);
    }

}
