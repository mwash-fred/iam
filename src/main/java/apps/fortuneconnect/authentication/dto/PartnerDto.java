package apps.fortuneconnect.authentication.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PartnerDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String uid,
        String partnerName,
        LocalDate onboardedDate,
        @JsonProperty("contact_person")
        @NotNull
        UserDto userDto
) {
}