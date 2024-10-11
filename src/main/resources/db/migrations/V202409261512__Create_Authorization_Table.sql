-- Migration to create the Authorization table

CREATE TABLE authorization_log
(
    sn                            BIGSERIAL PRIMARY KEY,
    uid                           UUID NOT NULL UNIQUE, -- UUID for the uid field
    registered_client_id          VARCHAR(255),         -- Corresponds to registeredClientId
    principal_name                VARCHAR(255),         -- Corresponds to principalName
    authorization_grant_type      VARCHAR(255),         -- Corresponds to authorizationGrantType
    authorized_scopes             TEXT,                 -- LOB for authorizedScopes
    attributes                    TEXT,                 -- LOB for attributes
    state                         VARCHAR(500),         -- Corresponds to state

    authorization_code_value      TEXT,                 -- LOB for authorizationCodeValue
    authorization_code_issued_at  TIMESTAMPTZ,          -- Corresponds to Instant authorizationCodeIssuedAt
    authorization_code_expires_at TIMESTAMPTZ,          -- Corresponds to Instant authorizationCodeExpiresAt
    authorization_code_metadata   TEXT,                 -- Changed to TEXT for consistency with other LOB fields

    access_token_value            TEXT,                 -- LOB for accessTokenValue
    access_token_issued_at        TIMESTAMPTZ,          -- Corresponds to Instant accessTokenIssuedAt
    access_token_expires_at       TIMESTAMPTZ,          -- Corresponds to Instant accessTokenExpiresAt
    access_token_metadata         TEXT,                 -- LOB for accessTokenMetadata
    access_token_type             VARCHAR(255),         -- Corresponds to accessTokenType
    access_token_scopes           TEXT,                 -- LOB for accessTokenScopes

    refresh_token_value           TEXT,                 -- LOB for refreshTokenValue
    refresh_token_issued_at       TIMESTAMPTZ,          -- Corresponds to Instant refreshTokenIssuedAt
    refresh_token_expires_at      TIMESTAMPTZ,          -- Corresponds to Instant refreshTokenExpiresAt
    refresh_token_metadata        TEXT,                 -- LOB for refreshTokenMetadata

    oidc_id_token_value           TEXT,                 -- LOB for oidcIdTokenValue
    oidc_id_token_issued_at       TIMESTAMPTZ,          -- Corresponds to Instant oidcIdTokenIssuedAt
    oidc_id_token_expires_at      TIMESTAMPTZ,          -- Corresponds to Instant oidcIdTokenExpiresAt
    oidc_id_token_metadata        TEXT,                 -- LOB for oidcIdTokenMetadata
    oidc_id_token_claims          TEXT,                 -- LOB for oidcIdTokenClaims

    user_code_value               TEXT,                 -- LOB for userCodeValue
    user_code_issued_at           TIMESTAMPTZ,          -- Corresponds to Instant userCodeIssuedAt
    user_code_expires_at          TIMESTAMPTZ,          -- Corresponds to Instant userCodeExpiresAt
    user_code_metadata            TEXT,                 -- LOB for userCodeMetadata

    device_code_value             TEXT,                 -- LOB for deviceCodeValue
    device_code_issued_at         TIMESTAMPTZ,          -- Corresponds to Instant deviceCodeIssuedAt
    device_code_expires_at        TIMESTAMPTZ,          -- Corresponds to Instant deviceCodeExpiresAt
    device_code_metadata          TEXT                  -- LOB for deviceCodeMetadata
);
