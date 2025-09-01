package com.pablodev.organizationservice.shared.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "processed_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedEventEntity {

    @Id
    private String eventId;
    private String subscriberId;

}
