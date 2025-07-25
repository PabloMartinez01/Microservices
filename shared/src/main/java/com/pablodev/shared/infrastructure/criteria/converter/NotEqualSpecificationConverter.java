package com.pablodev.shared.infrastructure.criteria.converter;

import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.FilterOperator;
import org.springframework.data.jpa.domain.Specification;

@FilterConverter(FilterOperator.NOT_EQUAL)
public class NotEqualSpecificationConverter<T> implements SpecificationConverter<T> {

    @Override
    public Specification<T> convert(Filter filter) {
        return (root, _, cb) ->
                cb.notEqual(root.get(filter.getField()), filter.getValue());
    }
}
