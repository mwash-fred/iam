package apps.fortuneconnect.authentication.dto;


import java.time.LocalDateTime;

public record LoginAttemptsDto(
        String uid,
        LocalDateTime loginTime,
        String eventType,
        boolean success,
        String ipAddress,
        LocalDateTime postedTime,
        LocalDateTime modifiedTime
) {
}
