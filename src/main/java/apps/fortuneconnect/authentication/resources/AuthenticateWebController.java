package apps.fortuneconnect.authentication.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static apps.fortuneconnect.authentication.resources.AuthenticateWebController.AUTH_REQUEST_PATH_PREFIX;

@Controller
@RequestMapping(AUTH_REQUEST_PATH_PREFIX)
public class AuthenticateWebController {
    public static final String AUTH_REQUEST_PATH_PREFIX = "auth";
    public static final String LOGIN_PATH = "login";

    @GetMapping(LOGIN_PATH)
    public String login() {
        return "login";
    }
}
