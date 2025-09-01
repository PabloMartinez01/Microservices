package com.pablodev.organizationservice.shared.domain;

public interface ProcessedEventRepository {

    void save(String eventId, String subscriberId);

    Integer count(String eventId, String subscriberId);
}
