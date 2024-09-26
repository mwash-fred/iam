package apps.fortuneconnect.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailDto(@NotBlank(message = "Message cannot be null")
                       @NotNull(message = "Message cannot be null")
                       String message, @NotNull(message = "To cannot be null") @NotBlank(message = "To cannot be blank")
                       String to, String from, @NotNull(message = "Mailer cannot be null")
                       @NotBlank(message = "Mailer cannot be blank")
                       @Email
                       String mailer, String cc, String bcc, String subject, String applicationName) {
}
