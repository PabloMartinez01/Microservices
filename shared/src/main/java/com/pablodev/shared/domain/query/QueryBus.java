package com.pablodev.shared.domain.query;

public interface QueryBus {
    <R extends QueryResponse> R ask(Query<R> query);
}
