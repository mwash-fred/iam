-- Migration for creating the User table

CREATE TABLE users
(
    sn                     BIGSERIAL    NOT NULL PRIMARY KEY,
    uid                    UUID         NOT NULL UNIQUE,        -- UID (primary key)
    first_name             VARCHAR(50)  NOT NULL,               -- First Name
    middle_name            VARCHAR(50),                         -- Middle Name
    last_name              VARCHAR(50)  NOT NULL,               -- Last Name
    email                  VARCHAR(150) NOT NULL UNIQUE,        -- Email (must be unique)
    phone_no               VARCHAR(12) UNIQUE,                  -- Phone Number (must be unique)
    password               VARCHAR(255),                        -- Password
    posted_time            TIMESTAMPTZ  NOT NULL DEFAULT NOW(), -- Creation timestamp
    modified_time          TIMESTAMPTZ  NOT NULL DEFAULT NOW(),-- Last modified timestamp
    deleted_flag           BOOLEAN      NOT NULL,               -- Is the user deleted?
    deleted_time           TIMESTAMPTZ,                         -- Timestamp when the user was deleted
    first_login            BOOLEAN      NOT NULL,               -- Is this the user's first login?
    is_enabled             BOOLEAN      NOT NULL,               -- Is the user enabled?
    is_credentials_expired BOOLEAN      NOT NULL,               -- Are the credentials expired?
    is_account_locked      BOOLEAN      NOT NULL,               -- Is the account locked?
    is_account_expired     BOOLEAN      NOT NULL,               -- Is the account expired?
    two_factor_enabled     BOOLEAN      NOT NULL,               -- Is two-factor authentication enabled?
    password_valid_till    TIMESTAMPTZ,                         -- Password expiry timestamp

    CONSTRAINT email_unique UNIQUE (email),                     -- Unique constraint on email
    CONSTRAINT phone_unique UNIQUE (phone_no)                   -- Unique constraint on phone number
);

-- Create table for tracking login attempts related to the user (if needed based on the LoginAttempt entity)
