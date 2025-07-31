package com.pablodev.organizationservice.subscription.infrastructure.persistence;

import com.pablodev.shared.infrastructure.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSubscriptionRepository extends BaseRepository<SubscriptionEntity, String> {

}
