package com.pablodev.shared.infrastructure.criteria.converter;


import com.pablodev.shared.domain.criteria.Filter;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationConverter<T> {
    Specification<T> convert(Filter filter);
}
