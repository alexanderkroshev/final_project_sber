package client.token;

import client.exception.AuthorizationException;
import client.exception.TokenNotFoundException;
import common.AuthenticationRequestDto;
import common.TokenDto;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Log
@Data
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
            AuthenticationRequestDto authentication = new AuthenticationRequestDto(
                    number, password);
            HttpHeaders authenticationHeaders = new HttpHeaders();
            HttpEntity<AuthenticationRequestDto> authEntity = new HttpEntity<>(authentication,
                    authenticationHeaders);
            ResponseEntity<TokenDto> response = restTemplate.exchange(AUTHORIZATION_URL,
                    HttpMethod.POST, authEntity, TokenDto.class);
            token = response.getBody().getToken();
        } catch (Exception e) {
            log.info("Incorrect login/password");
            throw new AuthorizationException("Incorrect login/password");
        }
    }

    public void logout() {
        HttpHeaders logoutHeaders = new HttpHeaders();
        logoutHeaders.set("Authorization", token);
        HttpEntity<?> logoutEntity = new HttpEntity<>(logoutHeaders);
        restTemplate.postForObject(LOGOUT_URL, logoutEntity, Void.class);
        token = null;
    }
}
