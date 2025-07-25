package com.pablodev.shared.domain.query;

public class QueryNotRegisteredException extends RuntimeException {

    public QueryNotRegisteredException(Class<? extends Query> queryClass) {
        super("Query %s is not registered".formatted(queryClass.getSimpleName()));
    }
}
