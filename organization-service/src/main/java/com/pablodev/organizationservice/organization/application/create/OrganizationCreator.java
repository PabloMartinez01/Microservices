package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.organizationservice.organization.domain.*;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationAlreadyExistsException;
import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationCreator {

    private final OrganizationRepository repository;

    public void create(CreateOrganizationCommand command) {

        Organization organization = createOrganization(command);

        ensureOrganizationNameNotExist(command.name());

        repository.save(organization);
    }

    private void ensureOrganizationNameNotExist(String name) {
        Criteria criteria = Criteria.of(Order.unordered(), Filter.equals("name", name));
        if (!repository.search(criteria).isEmpty()) {
            throw new OrganizationAlreadyExistsException(name);
        }
    }

    private Organization createOrganization(CreateOrganizationCommand command) {
        return Organization.create(
                command.id(),
                command.name(),
                command.type(),
                command.street(),
                command.city(),
                command.state(),
                command.country()
        );
    }
}
