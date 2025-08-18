package com.pablodev.shared.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DomainEventConfiguration {

    private String prefix;
}
