package apps.fortuneconnect.authentication.dao.attempt;

import apps.fortuneconnect.authentication.dto.LoginAttemptsDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class LoginAttemptService {
    private final LoginAttemptRepository loginAttemptRepository;

    public static LoginAttemptsDto loginAttemptsModelToDTO(LoginAttempt loginAttempt) {
        Function<LoginAttempt, LoginAttemptsDto> loginAttemptsDtoFunction = la -> new LoginAttemptsDto(la.getUid(),
                la.getLoginTime(), la.getEventType(), la.isSuccess(), la.getIpAddress(), la.getPostedTime(), la.getModifiedTime());

        return loginAttemptsDtoFunction.apply(loginAttempt);
    }
}
