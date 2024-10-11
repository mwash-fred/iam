package apps.fortuneconnect.authentication.dao.user;

import apps.fortuneconnect.authentication.dao.attempt.LoginAttempt;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;
    @UuidGenerator
    @Column(nullable = false, unique = true, name = "uid")
    private UUID uid;
    @Column(length = 50, nullable = false, name = "first_name")
    private String firstName;
    @Column(length = 50, name = "middle_name")
    private String middleName;
    @Column(length = 50, nullable = false, name = "last_name")
    private String lastName;
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;
    @Column(name = "phone_no", length = 12, unique = true)
    private String phoneNo;
    @Column(name = "password")
    private String password;
    @Column(nullable = false, updatable = false, name = "posted_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime postedTime;
    @Column(nullable = false, name = "modified_time")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime modifiedTime;
    @Column(nullable = false, name = "deleted_flag")
    private Boolean deletedFlag;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_time")
    private LocalDateTime deletedTime;
    @Column(name = "first_login", nullable = false)
    private Boolean firstLogin;
    @Column(nullable = false, name = "is_enabled")
    private Boolean isEnabled;
    @Column(nullable = false, name = "is_credentials_expired")
    private Boolean isCredentialsExpired;
    @Column(nullable = false, name = "is_account_locked")
    private Boolean isAccountLocked;
    @Column(nullable = false, name = "is_account_expired")
    private Boolean isAccountExpired;
    @Column(nullable = false, name = "two_factor_enabled")
    private Boolean twoFactorEnabled;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "password_valid_till")
    private LocalDateTime passwordValidTill;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoginAttempt> attempts;

    //TODO: Set up entity listener for pre persisting
}
