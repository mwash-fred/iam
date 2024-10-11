package apps.fortuneconnect.authentication.dao.attempt;

import apps.fortuneconnect.authentication.dao.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "event_log")
public class LoginAttempt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;
    @UuidGenerator
    @Column(nullable = false, unique = true)
    private UUID uid;
    @Column(nullable = false)
    private LocalDateTime loginTime;
    private String eventType;
    private boolean success;
    @Column(name = "ip_address", length = 15, nullable = false)
    private String ipAddress;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_sn", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime postedTime;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime modifiedTime;
}
