package apps.fortuneconnect.authentication.dao.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sn;
    @UuidGenerator
    @Column(nullable = false, unique = true)
    private UUID uid;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    @Column(length = 1000)
    private String clientAuthenticationMethods;
    @Column(length = 1000)
    private String authorizationGrantTypes;
    @Column(length = 1000)
    private String redirectUris;
    @Column(length = 1000)
    private String postLogoutRedirectUris;
    @Column(length = 1000)
    private String scopes;
    @Column(length = 2000)
    private String clientSettings;
    @Column(length = 2000)
    private String tokenSettings;
    @Column(length = 500)
    private String logoUri;
}
