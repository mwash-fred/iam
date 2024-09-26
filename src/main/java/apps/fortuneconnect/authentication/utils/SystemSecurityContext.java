package apps.fortuneconnect.authentication.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

public class SystemSecurityContext {
    public static void setSystemSecurityContext() {
        Authentication systemAuth = new UsernamePasswordAuthenticationToken(
                "SYSTEM", null,
                Collections.singletonList(() -> "ROLE_SYSTEM")
        );

        SecurityContext systemContext = SecurityContextHolder.createEmptyContext();
        systemContext.setAuthentication(systemAuth);

        SecurityContextHolder.setContext(systemContext);
    }
}
