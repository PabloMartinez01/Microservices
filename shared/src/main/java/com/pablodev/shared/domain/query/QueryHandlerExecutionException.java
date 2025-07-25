package com.pablodev.shared.domain.query;

public class QueryHandlerExecutionException extends RuntimeException {
    public QueryHandlerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
