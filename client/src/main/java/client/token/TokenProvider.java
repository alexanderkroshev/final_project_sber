package client.token;

import client.exception.AuthException;
import client.exception.TokenNotFoundException;
import common.dto.AuthDto;
import common.dto.TokenDto;
import common.Type;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Data
@Component
public class TokenProvider {
    private final RestTemplate restTemplate;
    private String token;
    private final String AUTHORIZATION_URL = "http://localhost:8080/api/v1/auth/login";
    private final String LOGOUT_URL = "http://localhost:8080/api/v1/auth/logout";

    public String getToken() {
        return Optional.ofNullable(token).
                orElseThrow(() -> new TokenNotFoundException("you have to sign in"));
    }

    public void login(String number, String password) {
        try {
            AuthDto authentication = new AuthDto(number, password, Type.CARD);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<AuthDto> authEntity = new HttpEntity<>(
                    authentication, headers
            );
            ResponseEntity<TokenDto> response = restTemplate.exchange(AUTHORIZATION_URL,
                    HttpMethod.POST, authEntity, TokenDto.class);
            token = response.getBody().getToken();
        } catch (Exception e) {
            String msg = "Error while login with card number: " + number;
            log.info(msg);
            throw new AuthException(msg, e);
        }
    }

    public void logout() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> logoutEntity = new HttpEntity<>(headers);
        restTemplate.postForObject(LOGOUT_URL, logoutEntity, Void.class);
        token = null;
    }
}
