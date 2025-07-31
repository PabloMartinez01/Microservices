package com.pablodev.organizationservice.subscription.infrastructure.persistence;

import com.pablodev.organizationservice.subscription.domain.Subscription;
import com.pablodev.organizationservice.subscription.domain.SubscriptionId;
import com.pablodev.organizationservice.subscription.domain.SubscriptionRepository;
import com.pablodev.organizationservice.subscription.domain.exceptions.SubscriptionDoesNotExistException;
import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.infrastructure.criteria.CriteriaConverter;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PostgresSubscriptionRepository implements SubscriptionRepository {

    private final JpaSubscriptionRepository repository;
    private final SubscriptionEntityMapper mapper;
    private final CriteriaConverter<SubscriptionEntity> criteriaConverter;

    public PostgresSubscriptionRepository(
            JpaSubscriptionRepository repository,
            SubscriptionEntityMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.criteriaConverter = new CriteriaConverter<>();
    }


    @Override
    public void save(Subscription subscription) {
        SubscriptionEntity entity = mapper.toEntity(subscription);
        repository.save(entity);
    }

    @Override
    public Subscription findById(SubscriptionId subscriptionId) {
        String id = subscriptionId.getValue();
        return repository.findById(id)
                .map(mapper::toAggregate)
                .orElseThrow(() -> new SubscriptionDoesNotExistException(id));
    }

    @Override
    public List<Subscription> search(Criteria criteria) {

        Sort sort = criteriaConverter.toSort(criteria);
        Specification<SubscriptionEntity> specification = criteriaConverter.toSpecification(
                criteria);

        return repository.findAll(specification, sort).stream()
                .map(mapper::toAggregate)
                .toList();
    }

    @Override
    public void deleteById(SubscriptionId subscriptionId) {
        repository.deleteById(subscriptionId.getValue());
    }


}
