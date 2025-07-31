CREATE TABLE subscriptions
(
    id              VARCHAR(255) NOT NULL,
    organization_id VARCHAR(255) NOT NULL,
    start_date      DATE         NOT NULL,
    expiration_date DATE         NOT NULL,
    cancelled       BOOLEAN      NOT NULL,

    CONSTRAINT fk_subscriptions_organization
        FOREIGN KEY (organization_id)
            REFERENCES organizations (id)
            ON DELETE CASCADE
)