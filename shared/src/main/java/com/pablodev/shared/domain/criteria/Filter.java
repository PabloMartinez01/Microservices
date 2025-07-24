package com.pablodev.shared.domain.criteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Filter {
    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;

    public static Filter equals(String field, String value) {
        return new Filter(new FilterField(field), FilterOperator.EQUAL, new FilterValue(value));
    }

    public static Filter notEqual(String field, String value) {
        return new Filter(new FilterField(field), FilterOperator.NOT_EQUAL, new FilterValue(value));
    }

    public static Filter greaterThan(String field, String value) {
        return new Filter(new FilterField(field), FilterOperator.GT, new FilterValue(value));
    }

    public static Filter lessThan(String field, String value) {
        return new Filter(new FilterField(field), FilterOperator.LT, new FilterValue(value));
    }

    public static Filter contains(String field, String value) {
        return new Filter(new FilterField(field), FilterOperator.CONTAINS, new FilterValue(value));
    }

    public static Filter notContains(String field, String value) {
        return new Filter(new FilterField(field), FilterOperator.NOT_CONTAINS, new FilterValue(value));
    }

    public String getField() {
        return field.getValue();
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public String getValue() {
        return value.getValue();
    }
}
