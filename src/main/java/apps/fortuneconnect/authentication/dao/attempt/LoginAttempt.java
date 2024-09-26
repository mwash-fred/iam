package apps.fortuneconnect.authentication.dao.attempt;

import apps.fortuneconnect.authentication.dao.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class LoginAttempt {
    @Id
    @UuidGenerator
    private String uid;
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
    private LocalDateTime postedTime;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedTime;

    @PrePersist
    private void prePersist(){
        this.uid = UUID.randomUUID().toString();
        this.postedTime = LocalDateTime.now();
        this.modifiedTime = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpLocalDateTime(){
        this.modifiedTime = LocalDateTime.now();
    }
}
