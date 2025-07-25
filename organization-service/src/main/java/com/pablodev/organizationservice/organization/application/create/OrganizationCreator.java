package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.organizationservice.organization.domain.Organization;
import com.pablodev.organizationservice.organization.domain.OrganizationRepository;
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

    public void create(
            String id,
            String name,
            String type,
            String street,
            String city,
            String state,
            String country) {

        ensureOrganizationNameNotExist(name);

        Organization organization = Organization.create(id, name, type, street, city, state,
                country);
        repository.save(organization);
    }

    private void ensureOrganizationNameNotExist(String name) {
        Criteria criteria = Criteria.of(Order.unordered(), Filter.equals("name", name));
        if (!repository.search(criteria).isEmpty()) {
            throw new OrganizationAlreadyExistsException(name);
        }
    }


}
