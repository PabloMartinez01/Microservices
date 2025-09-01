package com.pablodev.organizationservice.shared.infrastructure;

import com.pablodev.organizationservice.shared.domain.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgresProcessedEventRepository implements ProcessedEventRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(String eventId, String subscriberId) {
        jdbcTemplate.update("""
                    INSERT INTO processed_events (event_id, subscriber_id) VALUES (?, ?)
                    ON CONFLICT (event_id, subscriber_id) DO NOTHING
                """);
    }

    @Override
    public Integer count(String eventId, String subscriberId) {
        return jdbcTemplate.queryForObject("""
                SELECT COUNT(*) FROM processed_events WHERE event_id=? AND subscriber_id=?
                """, Integer.class);
    }
}
