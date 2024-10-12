package apps.fortuneconnect.authentication.resources;

import apps.fortuneconnect.authentication.ui.LoginModelForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static apps.fortuneconnect.authentication.resources.AuthenticateWebController.AUTH_REQUEST_PATH_PREFIX;

@Controller @Slf4j
@RequestMapping(AUTH_REQUEST_PATH_PREFIX)
public class AuthenticateWebController {
    public static final String AUTH_REQUEST_PATH_PREFIX = "auth";
    public static final String LOGIN_PATH = "login";

    @GetMapping(LOGIN_PATH)
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginModelForm());
        return "login";
    }

    @PostMapping(LOGIN_PATH)
    public String submitLoginForm(@ModelAttribute LoginModelForm loginModelForm, Model model){
        String email = loginModelForm.getEmail();
        String password = loginModelForm.getPassword();

        model.addAttribute("email", email);
        model.addAttribute("password", password);

        log.info("The user {} is trying to login with password {}", email, password);

        return "signup";
    }
}
