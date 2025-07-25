package com.pablodev.shared.infrastructure.criteria;

import com.pablodev.shared.domain.criteria.Criteria;
import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.FilterOperator;
import com.pablodev.shared.domain.criteria.Order;
import com.pablodev.shared.infrastructure.criteria.converter.FilterConverter;
import com.pablodev.shared.infrastructure.criteria.converter.SpecificationConverter;
import org.reflections.Reflections;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CriteriaConverter<T> {

    private final Map<FilterOperator, SpecificationConverter<T>> specifications;

    @SuppressWarnings("unchecked")
    public CriteriaConverter() {

        specifications = new EnumMap<>(FilterOperator.class);
        Reflections reflections = new Reflections("com.pablodev.shared.infrastructure.criteria.converter");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(FilterConverter.class);

        try {
            for  (Class<?> clazz : classes) {
                FilterOperator value = clazz.getAnnotation(FilterConverter.class).value();
                SpecificationConverter<T> converter = (SpecificationConverter<T>) clazz.getDeclaredConstructor().newInstance();
                specifications.put(value, converter);
            }
        }
        catch (Exception e) {
            throw new CriteriaConverterException(e.getMessage());
        }

    }

    private Specification<T> getSpecification(Filter filter) {
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
