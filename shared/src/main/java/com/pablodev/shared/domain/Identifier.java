package com.pablodev.shared.domain;

import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class Identifier implements Serializable {

    protected final String value;

    public Identifier(String value) {
        ensureValidUuid(value);
        this.value = value;
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }

}
