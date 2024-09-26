package apps.fortuneconnect.authentication;

import apps.fortuneconnect.authentication.dao.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AuthenticationApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Bean
	ApplicationRunner clientsRunner(RegisteredClientRepository repository, UserRepository userRepository) {
		return args -> {
			var clientId = "test-client";
			if (repository.findByClientId(clientId) == null) {
				repository.save(
						RegisteredClient.withId(UUID.randomUUID().toString())
								.clientId(clientId)
								.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
								.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
								.clientSecret("secret")
								.redirectUris((uris) -> uris.addAll(List.of("http://127.0.0.1:4200/dashboard")))
								.scope("profile")
								.scope("openid")
								.build()
				);
			}
		};
	}
}
