package com.pablodev.shared.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class DomainEvent {

    private String id;
    private LocalDateTime timestamp;

    protected DomainEvent() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }
    
    public abstract String getEventName();

}
