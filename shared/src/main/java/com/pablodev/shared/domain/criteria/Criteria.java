package com.pablodev.shared.domain.criteria;

import java.util.Arrays;
import java.util.List;


public record Criteria(
        Order order,
        List<Filter> filters) {

    public static Criteria of(Order order, Filter... filter) {
        return new Criteria(order, Arrays.stream(filter).toList());
    }

    public static Criteria empty() {
        return new Criteria(Order.unordered(), List.of());
    }

}
