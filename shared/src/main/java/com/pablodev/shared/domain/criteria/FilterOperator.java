package com.pablodev.shared.domain.criteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FilterOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    CONTAINS("CONTAINS"),
    NOT_CONTAINS("NOT_CONTAINS");

    private final String operator;

    public static FilterOperator fromValue(String value) {
        return switch (value) {
            case "=" -> FilterOperator.EQUAL;
            case "!=" -> FilterOperator.NOT_EQUAL;
            case ">" -> FilterOperator.GT;
            case "<" -> FilterOperator.LT;
            case "CONTAINS" -> FilterOperator.CONTAINS;
            case "NOT_CONTAINS" -> FilterOperator.NOT_CONTAINS;
            default -> null;
        };
    }

    public String value() {
        return operator;
    }
}
