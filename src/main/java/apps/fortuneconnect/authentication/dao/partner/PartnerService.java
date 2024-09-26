package apps.fortuneconnect.authentication.dao.partner;

import apps.fortuneconnect.authentication.dao.user.User;
import apps.fortuneconnect.authentication.dao.user.UserService;
import apps.fortuneconnect.authentication.dto.PartnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PartnerService {
    private final PartnerRepository partnerRepository;

    public PartnerDto create(PartnerDto partnerDto){
        Partner partner = partnerRepository
                .save(partnerDTOToModel(partnerDto));
        User user = UserService.userDTOToModel(partnerDto.userDto());
        return partnerModelToDTO(partner);
    }

    public static PartnerDto partnerModelToDTO(Partner partner) {
        Function<Partner, PartnerDto> partnerDtoFunction = dto -> new PartnerDto(partner.getUid(),
                partner.getPartnerName(),
                partner.getOnboardedDate(), null);
        return partnerDtoFunction.apply(partner);
    }

    public static Partner partnerDTOToModel(PartnerDto dto) {
        Function<PartnerDto, Partner> partnersFunction = partner -> new Partner(null, partner.partnerName(), partner
                .onboardedDate(), null, null, null, null);
        return partnersFunction.apply(dto);
    }
}
