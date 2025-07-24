package com.pablodev.shared.infrastructure.criteria.converter;

import com.pablodev.shared.domain.criteria.FilterOperator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterConverter {
    FilterOperator value();
}
