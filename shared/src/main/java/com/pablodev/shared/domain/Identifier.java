package com.pablodev.shared.domain;

import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Identifier implements Serializable {

    protected String value;

    public Identifier(String value) {
        ensureValidUuid(value);
        this.value = value;
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }

}
