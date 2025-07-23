package com.pablodev.shared.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class Criteria {
    private final Order order;
    private final List<Filter> filters;

    public static Criteria of(Order order, Filter... filter) {
        return new Criteria(order,  Arrays.stream(filter).toList());
    }

    public static Criteria empty() {
        return new Criteria(Order.unordered(), List.of());
    }

}
