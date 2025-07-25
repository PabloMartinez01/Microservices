package com.pablodev.shared.domain.query;

public interface QueryHandler<Q extends Query<R>, R extends QueryResponse> {
    R handle(Q query);
}
