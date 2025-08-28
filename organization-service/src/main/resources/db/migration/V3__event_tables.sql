CREATE TABLE processed_events
(
    event_id        VARCHAR(255) NOT NULL,
    subscription_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (event_id, subscription_id)
)