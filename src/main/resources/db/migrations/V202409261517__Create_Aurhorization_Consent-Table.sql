-- Migration to create the AuthorizationConsent table with a composite primary key

CREATE TABLE authorization_consent
(
    registered_client_id VARCHAR(255) NOT NULL,        -- Corresponds to registeredClientId
    principal_name       VARCHAR(255) NOT NULL,        -- Corresponds to principalName
    authorities          VARCHAR(1000),                -- Corresponds to authorities

    PRIMARY KEY (registered_client_id, principal_name) -- Composite primary key
);
