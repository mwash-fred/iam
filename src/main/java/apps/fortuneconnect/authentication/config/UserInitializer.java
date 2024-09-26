package apps.fortuneconnect.authentication.config;

import apps.fortuneconnect.authentication.dao.user.User;
import apps.fortuneconnect.authentication.dao.user.UserRepository;
import apps.fortuneconnect.authentication.utils.SystemSecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserInitializer implements ApplicationRunner {
    private static final String ADMIN = "admin@gmail.com";
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        try {
            initializer();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void initializer() {

        SystemSecurityContext.setSystemSecurityContext();


        if (!usersRepository.existsByEmail(ADMIN)) {
            String password = "password";

            User user = User.builder()
                    .phoneNo("254708881885")
                    .verifiedFlag(true)
                    .verifiedTime(LocalDateTime.now())
                    .email("mwangiwilly395@gmail.com")
                    .firstName("Wilfred")
                    .middleName("Mwangi")
                    .lastName("Njuguna")
                    .password(passwordEncoder.encode(password))
                    .firstLogin(false)
                    .deletedFlag(false)
                    .twoFactorEnabled(false)
                    .build();

            usersRepository.save(user);
        }
    }
}
