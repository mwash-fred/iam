package apps.fortuneconnect.authentication.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class LoginModelForm {
    private String email;
    private String password;
}
