package com.pablodev.shared.infrastructure.event.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.kafka")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KafkaConfigurationProperties {

    private String consumerPrefix;
}
