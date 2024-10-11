package apps.fortuneconnect.authentication.resources;

import apps.fortuneconnect.authentication.dao.client.ClientService;
import apps.fortuneconnect.authentication.dao.user.UserService;
import apps.fortuneconnect.authentication.dto.ClientDto;
import apps.fortuneconnect.authentication.dto.PartnerDto;
import apps.fortuneconnect.authentication.dto.ResponseTemplate;
import apps.fortuneconnect.authentication.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/auth")
@RestController @Slf4j
@RequiredArgsConstructor
public class AuthenticateRestController {
    private final UserService userService;
    private final ClientService clientService;

    @PostMapping(value = "register-user", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto){
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseTemplate<Void>("User Created successfully", null, null)
        );
    }

    @PostMapping(value = "register-client-app",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClientApplication(@RequestBody @Valid ClientDto clientDto){
        clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseTemplate<Void>("Client Application Created successfully", null, null)
        );
    }

    @GetMapping(value = "clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listClientApplications() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseTemplate<>("Client Application Created successfully",
                        clientService.listAllClientApplications(), null)
        );
    }
}
