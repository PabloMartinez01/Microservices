package com.pablodev.shared.domain;

import java.io.Serializable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Identifier implements Serializable {

    protected String value;

    public Identifier(String value) {
        ensureValidUuid(value);
        this.value = value;
    }

    private void ensureValidUuid(String value) {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdentifierException(value);
        }

    }

}
