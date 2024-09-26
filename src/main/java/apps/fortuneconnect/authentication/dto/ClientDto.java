package apps.fortuneconnect.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ClientDto(
        @JsonProperty("client_name") String clientName,
        @JsonProperty("grant_types") List<String> grantTypes,
        @JsonProperty("redirect_uris") List<String> redirectUris,
        @JsonProperty("logo_uri") String logoUri,
        @JsonProperty(value = "client_id", access = JsonProperty.Access.READ_ONLY)
        String clientId,
        @JsonProperty(value = "client_secret", access = JsonProperty.Access.READ_ONLY)
        String clientSecret,
        List<String> scope
) {}