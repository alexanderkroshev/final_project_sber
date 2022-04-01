package client.savetoken;

import client.auth.ResponseToken;
import client.exception.AuthorizationException;
import client.exception.TokenNotFoundException;
import common.AuthenticationRequestDto;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Data
@Log
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
            ResponseEntity<ResponseToken> response = restTemplate.exchange(AUTHORIZATION_URL,
                    HttpMethod.POST, authEntity, ResponseToken.class);
            token = response.getBody().getToken();//Optional.ofNullable(response.getBody()
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
