package com.pablodev.shared.domain.criteria;

import com.pablodev.shared.domain.ValueObject;

public class OrderBy extends ValueObject<String> {
    public OrderBy(String value) {
        super(value);
    }
}
