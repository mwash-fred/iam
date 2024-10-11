-- Migration to create the Client table without the partner foreign key

CREATE TABLE clients
(
    sn                            BIGSERIAL PRIMARY KEY,
    uid                           UUID NOT NULL, -- UUID for uid field
    client_id                     VARCHAR(255),  -- Corresponds to clientId
    client_id_issued_at           TIMESTAMPTZ,   -- Corresponds to Instant clientIdIssuedAt
    client_secret                 VARCHAR(255),  -- Corresponds to clientSecret
    client_secret_expires_at      TIMESTAMPTZ,   -- Corresponds to Instant clientSecretExpiresAt
    client_name                   VARCHAR(255),  -- Corresponds to clientName
    client_authentication_methods VARCHAR(1000), -- Corresponds to clientAuthenticationMethods
    authorization_grant_types     VARCHAR(1000), -- Corresponds to authorizationGrantTypes
    redirect_uris                 VARCHAR(1000), -- Corresponds to redirectUris
    post_logout_redirect_uris     VARCHAR(1000), -- Corresponds to postLogoutRedirectUris
    scopes                        VARCHAR(1000), -- Corresponds to scopes
    client_settings               VARCHAR(2000), -- Corresponds to clientSettings
    token_settings                VARCHAR(2000), -- Corresponds to tokenSettings
    logo_uri                      VARCHAR(500)   -- Corresponds to logoUri
);
