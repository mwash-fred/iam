package apps.fortuneconnect.authentication.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MessageDto(
        @Pattern(regexp = "^(07|01\\d{1})\\d{7}$|^(2547|2541)\\d{8}$", message = "Recipient must be a valid Kenyan phone number")
        @Size(min = 10, max = 12, message = "Recipient length must be between {min} and {max} characters")
        String recipient,
        String message,
        String shortCode
) {
}
