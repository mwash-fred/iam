package apps.fortuneconnect.authentication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseTemplate<T>(
        String message,
        T data,
        String errors
) { }
