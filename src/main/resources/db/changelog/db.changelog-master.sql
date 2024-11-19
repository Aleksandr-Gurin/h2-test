--liquibase formatted sql

-- include your migration scripts
-- changeset author:initial-schema
CREATE TABLE person (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        age INT NOT NULL
);
