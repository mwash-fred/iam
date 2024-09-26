package apps.fortuneconnect.authentication.dao.PartnerUser;

import apps.fortuneconnect.authentication.dao.partner.Partner;
import apps.fortuneconnect.authentication.dao.user.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter @Setter
public class PartnerUser {
    @EmbeddedId
    private PartnerUserId id;
    @ManyToOne
    @MapsId("partnerUid")
    private Partner partner;
    @ManyToOne
    @MapsId("userUid")
    private User user;
    private String userType;

    @Getter @Setter
    private static class PartnerUserId implements Serializable{
        private String partnerUid;
        private String userUid;
    }
}
