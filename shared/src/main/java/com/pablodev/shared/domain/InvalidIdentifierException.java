package com.pablodev.shared.domain;

import java.text.MessageFormat;

public class InvalidIdentifierException extends RuntimeException {

    public InvalidIdentifierException(String id) {
        super(MessageFormat.format("Invalid identifier {0}", id));
    }
}
