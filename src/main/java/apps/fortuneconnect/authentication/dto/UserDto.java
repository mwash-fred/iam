package apps.fortuneconnect.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record UserDto(
        String uid,
        @NotNull(message = "First Name cannot be null")
        @NotBlank(message = "First Name cannot be blank")
        @NotEmpty(message = "First Name cannot be empty")
        String firstName,
        String middleName,
        @NotNull(message = "Surname cannot be null")
        @NotBlank(message = "Surname cannot be blank")
        @NotEmpty(message = "Surname cannot be empty")
        String lastName,
        @Email
        String email,
        @Size(min = 10, max = 12, message = "Phone Number can only be between and 12 characters ")
        String phoneNo,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDateTime postedTime,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDateTime modifiedTime,
        LocalDateTime verifiedTime,
        Boolean verifiedFlag,
        Boolean deletedFlag,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Boolean firstLogin,
        Boolean isEnabled,
        Boolean isCredentialsExpired,
        Boolean isAccountLocked,
        Boolean isAccountExpired,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        List<LoginAttemptsDto> attempts,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Pattern(regexp = "^(?=.*[!@#$%^&*()-_+=<>?])(?=.*[A-Z])(?=.*[a-z]).{8,}$",
                message = "Password does not match expected pattern")
        String password
) {
}
