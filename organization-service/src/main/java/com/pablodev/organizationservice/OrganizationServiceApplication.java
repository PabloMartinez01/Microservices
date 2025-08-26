package com.pablodev.organizationservice;

import com.pablodev.shared.domain.event.EventBus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
        "com.pablodev.organizationservice",
        "com.pablodev.shared"
})
public class OrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(EventBus eventBus) {
        return args -> {

        };
    }

}
