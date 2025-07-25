package com.pablodev.shared.infrastructure.query;

import com.pablodev.shared.domain.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InMemoryQueryBus implements QueryBus {

    private final QueryRegistry queryRegistry;

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public QueryResponse ask(Query query) {
        try {
            QueryHandler queryHandler = queryRegistry.getQueryHandler(query.getClass());
            return queryHandler.handle(query);
        } catch (Exception e) {
            throw new QueryHandlerExecutionException(e.getMessage(), e);
        }
    }

}
