CREATE TABLE processed_events
(
    event_id      VARCHAR(255) NOT NULL,
    subscriber_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (event_id, subscriber_id)
)