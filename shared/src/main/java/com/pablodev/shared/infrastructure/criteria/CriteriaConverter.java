package com.pablodev.shared.infrastructure.criteria;

import com.pablodev.shared.domain.criteria.*;
import com.pablodev.shared.infrastructure.criteria.converter.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class CriteriaConverter<T> {

    private final Map<FilterOperator, SpecificationConverter<T>> specifications;

    public CriteriaConverter() {
        specifications = new EnumMap<>(FilterOperator.class);
        specifications.put(FilterOperator.EQUAL, new EqualSpecificationConverter<>());
        specifications.put(FilterOperator.NOT_EQUAL, new NotEqualSpecificationConverter<>());
        specifications.put(FilterOperator.CONTAINS, new ContainsSpecificationConverter<>());
        specifications.put(FilterOperator.NOT_CONTAINS, new NotEqualSpecificationConverter<>());
        specifications.put(FilterOperator.LT, new LowerThanSpecificationConverter<>());
        specifications.put(FilterOperator.GT, new GreaterThanSpecificationConverter<>());
    }

    public Specification<T> getSpecification(Filter filter) {
        return specifications.get(filter.getOperator()).convert(filter);
    }

    public Specification<T> toSpecification(Criteria criteria) {
        List<Specification<T>> specificationStream = criteria.getFilters()
                .stream()
                .map(this::getSpecification)
                .toList();
        return Specification.allOf(specificationStream);
    }

    public Sort toSort(Criteria criteria) {
        Order order = criteria.getOrder();

        if (order.hasOrder())
            return Sort.unsorted();

        Sort.Direction direction = order.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        return Sort.by(direction, order.getOrderBy());
    }


}
