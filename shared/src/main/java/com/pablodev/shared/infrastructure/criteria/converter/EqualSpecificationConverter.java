package com.pablodev.shared.infrastructure.criteria.converter;

import com.pablodev.shared.domain.criteria.Filter;
import com.pablodev.shared.domain.criteria.FilterOperator;
import org.springframework.data.jpa.domain.Specification;

@FilterConverter(FilterOperator.EQUAL)
public class EqualSpecificationConverter<T> implements SpecificationConverter<T> {

    @Override
    public Specification<T> convert(Filter filter) {
        return (root, _, cb) ->
                cb.equal(root.get(filter.getField()), filter.getValue());
    }

}
