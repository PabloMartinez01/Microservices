package com.pablodev.organizationservice.shared.application;

import com.pablodev.organizationservice.shared.domain.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProcessedEventService {

    private final ProcessedEventRepository repository;

    public void save(String eventId, String subscriberId) {
        repository.save(eventId, subscriberId);
    }

    @Transactional(readOnly = true)
    public boolean hasBeenProcessed(String eventId, String subscriberId) {
        return repository.count(eventId, subscriberId) > 0;
    }

}
