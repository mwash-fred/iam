-- Migration to create the Login Attempts Table

CREATE TABLE event_log
(
    sn            BIGSERIAL PRIMARY KEY,
    uid           UUID        NOT NULL UNIQUE , -- UUID type for the uid field
    login_time    TIMESTAMP   NOT NULL, -- Corresponds to LocalDateTime
    event_type    VARCHAR(255),         -- Corresponds to String
    success       BOOLEAN     NOT NULL, -- Corresponds to boolean
    ip_address    VARCHAR(15) NOT NULL, -- IP address field with length constraint
    user_uid      UUID        NOT NULL, -- Foreign key to user (assumed to be a BIGINT)
    posted_time   TIMESTAMP   NOT NULL, -- Creation timestamp (non-updatable)
    modified_time TIMESTAMP   NOT NULL  -- Modification timestamp
);

-- Adding the foreign key constraint for the user_sn column
ALTER TABLE event_log
    ADD CONSTRAINT fk_event_log_user
        FOREIGN KEY (user_uid)
            REFERENCES users (uid) -- Assuming the User table has an id column (adjust if necessary)
            ON DELETE CASCADE; -- Cascade deletion of logs when a user is deleted
