package apps.fortuneconnect.authentication.dao.authorization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "authorization_log")
public class Authorization {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;
    @UuidGenerator
    @Column(nullable = false, unique = true)
    private UUID uid;
    private String registeredClientId;
    private String principalName;
    private String authorizationGrantType;
    private String authorizedScopes;
    private String attributes;
    private String state;

    private String authorizationCodeValue;
    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;
    private String authorizationCodeMetadata;

    private String accessTokenValue;
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    private String accessTokenMetadata;
    private String accessTokenType;
    private String accessTokenScopes;

    private String refreshTokenValue;
    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    private String refreshTokenMetadata;

    private String oidcIdTokenValue;
    private Instant oidcIdTokenIssuedAt;
    private Instant oidcIdTokenExpiresAt;
    private String oidcIdTokenMetadata;
    private String oidcIdTokenClaims;

    private String userCodeValue;
    private Instant userCodeIssuedAt;
    private Instant userCodeExpiresAt;
    private String userCodeMetadata;

    private String deviceCodeValue;
    private Instant deviceCodeIssuedAt;
    private Instant deviceCodeExpiresAt;
    private String deviceCodeMetadata;
}
