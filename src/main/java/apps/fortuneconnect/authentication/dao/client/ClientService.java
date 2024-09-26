package apps.fortuneconnect.authentication.dao.client;

import apps.fortuneconnect.authentication.dto.ClientDto;
import apps.fortuneconnect.authentication.utils.ClientIDSecretGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final JpaRegisteredClientRepository jpaRegisteredClientRepository;

    public void createClient(ClientDto clientDto){
        RegisteredClient.Builder rc = RegisteredClient.withId(null)
                .clientId(String.format("C_%s", ClientIDSecretGenerator.generateBase36(15)))
                .clientName(clientDto.clientName())
                .clientSecret(String.format("S_%s", ClientIDSecretGenerator.generateBase36(8)))
                .clientIdIssuedAt(Instant.now())
                .redirectUris(uris -> uris.addAll(clientDto.redirectUris()))
                .authorizationGrantTypes(grantTypes -> {
                    Set<AuthorizationGrantType> grantTypeSet = clientDto.grantTypes()
                            .stream()
                            .map(AuthorizationGrantType::new)
                            .collect(Collectors.toSet());
                    grantTypes.addAll(grantTypeSet);
                })
                .scope(StringUtils.collectionToCommaDelimitedString(clientDto.scope()));
        jpaRegisteredClientRepository.save(rc.build());
    }

    public List<ClientDto> listAllClientApplications(){
        return clientRepository.findAll().stream().map(this::clientModelToDto).collect(Collectors.toList());
    }

    public ClientDto clientModelToDto(Client client){
        Function<Client, ClientDto> clientDtoFunction = c -> new ClientDto(c.getClientName(),
                StringUtils.commaDelimitedListToSet(c.getAuthorizationGrantTypes()).stream().toList(),
                StringUtils.commaDelimitedListToSet(c.getPostLogoutRedirectUris()).stream().toList(), c.getLogoUri(),
                client.getClientId(), c.getClientSecret(),
                StringUtils.commaDelimitedListToSet(c.getScopes()).stream().toList());
        return clientDtoFunction.apply(client);
    }
}
