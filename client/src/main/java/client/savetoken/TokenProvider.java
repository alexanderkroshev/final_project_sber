package client.savetoken;

import client.auth.ResponseToken;
import common.AuthenticationRequestDto;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Data
public class TokenProvider {
    private final RestTemplate restTemplate;
    private String token;
    private final String AUTHORIZATION_URL = "http://localhost:8080/api/v1/auth/login";

    public String getToken() {
        return Optional.ofNullable(token).orElseThrow(()->new RuntimeException("you have to sign in"));
    }

    public void login(String number, String password) {
        AuthenticationRequestDto authentication = new AuthenticationRequestDto(
                number, password);
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<AuthenticationRequestDto> authEntity = new HttpEntity<>(authentication,
                authenticationHeaders);
        token = restTemplate.exchange(AUTHORIZATION_URL,
                HttpMethod.POST, authEntity, ResponseToken.class).getBody().getToken();
    }

    public void logout() {
        token = null;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
