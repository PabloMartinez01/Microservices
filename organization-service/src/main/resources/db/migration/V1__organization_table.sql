CREATE TABLE organizations
(
    id      UUID         NOT NULL,
    name    VARCHAR(255) NOT NULL UNIQUE,
    street  VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    state   VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    type    VARCHAR(255) NOT NULL,

    CONSTRAINT pk_organizations PRIMARY KEY (id)
);