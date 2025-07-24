package com.pablodev.organizationservice;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.infrastructure.persistence.PostgreSqlOrganizationRepository;
import com.pablodev.shared.domain.criteria.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PostgreSqlOrganizationRepository repository) {
        return args -> {

            List<Organization> search = repository.search(
                    Criteria.of(
                            Order.ascending("name"),
                            Filter.equals("city", "Madrid")
                    )
            );

            System.out.println(search);

        };
    }

}
