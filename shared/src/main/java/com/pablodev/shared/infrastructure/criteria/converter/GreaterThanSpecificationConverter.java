package com.pablodev.shared.infrastructure.criteria.converter;

import com.pablodev.shared.domain.criteria.Filter;
import org.springframework.data.jpa.domain.Specification;

public class GreaterThanSpecificationConverter<T>  implements SpecificationConverter<T> {
    @Override
    public Specification<T> convert(Filter filter) {
        return (root, _, cb) ->
                cb.greaterThan(root.get(filter.getField()), String.format("%%%s%%", filter.getValue()));
    }
}
