package com.pablodev.shared.domain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class DomainEvent {

    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    protected DomainEvent() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }
    
}
