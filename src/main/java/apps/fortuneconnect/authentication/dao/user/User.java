package apps.fortuneconnect.authentication.dao.user;

import apps.fortuneconnect.authentication.dao.PartnerUser.PartnerUser;
import apps.fortuneconnect.authentication.dao.attempt.LoginAttempt;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true, updatable = false)
    private String uid;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50)
    private String middleName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;
    @Column(name = "phone", length = 12, unique = true)
    private String phoneNo;
    @Column(name = "password")
    private String password;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime postedTime;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime modifiedTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime verifiedTime;
    @Column(nullable = false)
    private Boolean verifiedFlag;
    @Column(nullable = false)
    private Boolean deletedFlag;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedTime;
    @Column(name = "first_login", nullable = false)
    private Boolean firstLogin;
    @Column(nullable = false)
    private Boolean isEnabled;
    @Column(nullable = false)
    private Boolean isCredentialsExpired;
    @Column(nullable = false)
    private Boolean isAccountLocked;
    @Column(nullable = false)
    private Boolean isAccountExpired;
    @Column(nullable = false)
    private Boolean twoFactorEnabled;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime passwordValidTill;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LoginAttempt> attempts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartnerUser> partnerUsers;

    @PrePersist
    private void prePersist() {
        this.isAccountLocked = false;
        this.isCredentialsExpired = false;
        this.firstLogin = true;
        this.isAccountExpired = false;
        this.isEnabled = false;
        this.verifiedFlag = false;
    }
}
