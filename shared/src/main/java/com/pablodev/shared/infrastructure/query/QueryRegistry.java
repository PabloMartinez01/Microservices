package com.pablodev.shared.infrastructure.query;

import com.pablodev.shared.domain.query.Query;
import com.pablodev.shared.domain.query.QueryHandler;
import com.pablodev.shared.domain.query.QueryNotRegisteredException;
import com.pablodev.shared.domain.query.QueryResponse;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class QueryRegistry {

    private final Map<Class<? extends Query<?>>, QueryHandler<? extends Query<?>, ? extends QueryResponse>> queryHandlers;
    private final ApplicationContext applicationContext;

    public QueryRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.queryHandlers = new HashMap<>();
        constructQueryHandlersMap();
    }

    public QueryHandler<? extends Query<?>, ? extends QueryResponse> getQueryHandler(
            Class<? extends Query> queryClass) {
        return Optional.ofNullable(queryHandlers.get(queryClass))
                .orElseThrow(() -> new QueryNotRegisteredException(queryClass));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void constructQueryHandlersMap() {
        Reflections reflections = new Reflections("com.pablodev");
        for (Class<? extends QueryHandler> queryHandlerClass : reflections.getSubTypesOf(
                QueryHandler.class)) {

            QueryHandler<?, ?> queryHandler = applicationContext.getBean(queryHandlerClass);
            ParameterizedType parameterizedType = (ParameterizedType) queryHandlerClass.getGenericInterfaces()[0];
            Class<? extends Query<? extends QueryResponse>> queryClass =
                    (Class<? extends Query<? extends QueryResponse>>) parameterizedType.getActualTypeArguments()[0];
            queryHandlers.put(queryClass, queryHandler);
        }


    }

}
