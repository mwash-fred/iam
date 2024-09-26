package apps.fortuneconnect.authentication.utils.integrations;

import apps.fortuneconnect.authentication.dto.EmailDto;
import apps.fortuneconnect.authentication.dto.MessageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor @Slf4j
public class Alerts {
    private final RestTemplate restTemplate;
    @Value(("${application.email.from}"))
    private String fromEmail;
    @Value(("${application.name}"))
    private String applicationName;
    public void sendMessage(@Valid MessageDto messageDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessageDto> request = new HttpEntity<>(messageDto, headers);
        try {
            String smsEndpoint = "http://ALERTS-APPLICATION/api/v1/sms/send";
            restTemplate.exchange(smsEndpoint, HttpMethod.POST, request, Void.class);
        } catch (RestClientException e) {
            log.error("Error sending SMS {}", e.getMessage());
        }
    }
    public void sendEmail(String message, String to, String cc, String subject){
        EmailDto emailDto = new EmailDto(message, to, this.fromEmail, this.fromEmail, cc,null, subject,
                this.applicationName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmailDto> request = new HttpEntity<>(emailDto, headers);
        String emailEndpoint = "http://ALERTS-APPLICATION/api/v1/mailer/send-mail";
        try {
            restTemplate.exchange(emailEndpoint, HttpMethod.POST, request, Void.class);
        } catch (RestClientException e) {
            log.error("Error sending Email {}", e.getMessage());
            throw new RuntimeException("There was a problem trying to send email. Try again later.");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("There was a problem trying to send email. Try again later.");
        }
    }
}
