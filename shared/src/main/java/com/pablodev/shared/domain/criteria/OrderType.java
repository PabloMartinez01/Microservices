package com.pablodev.shared.domain.criteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderType {
    ASCENDING("asc"),
    DESCENDING("desc"),
    NONE("none");
    private final String type;

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isAscending() {
        return this == ASCENDING;
    }

    public String value() {
        return type;
    }
}
