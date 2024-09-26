package apps.fortuneconnect.authentication.dao.partner;

import apps.fortuneconnect.authentication.dao.PartnerUser.PartnerUser;
import apps.fortuneconnect.authentication.dao.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Partner implements Serializable {
    @Id
    @UuidGenerator
    @Column(length = 36, nullable = false, updatable = false, unique = true)
    private String uid;
    @Column(length = 50, nullable = false)
    private String partnerName;
    @Temporal(TemporalType.DATE)
    private LocalDate onboardedDate;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime postedTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime modifiedTime;
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartnerUser> partnerUsers;
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Client> clients;
}
