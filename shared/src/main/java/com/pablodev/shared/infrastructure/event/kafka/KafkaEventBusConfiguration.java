package com.pablodev.shared.infrastructure.event.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablodev.shared.domain.event.MockUserCreatedDomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;


@Slf4j
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaEventBusConfiguration {

    private final ObjectMapper objectMapper;

    @KafkaListener(topicPattern = ".*")
    public void consume(String domainEvent) throws JsonProcessingException {

        MockUserCreatedDomainEvent domainEvent1 = objectMapper.readValue(domainEvent,
                MockUserCreatedDomainEvent.class);

        System.out.println(domainEvent);
    }


}

