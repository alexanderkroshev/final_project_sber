package client.service;

import client.auth.ResponseToken;
import common.AuthenticationRequestDto;
import common.BalanceDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CardService {

    private RestTemplate restTemplate;
    private final String AUTHORIZATION_URL = "http://localhost:8080/api/v1/auth/login";
    private final String BALANCE_URL = "http://localhost:8080/card/balance";

    public BalanceDto getBalance(String number, String password) {
        BalanceDto balanceDto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<ResponseToken> authResponse = getAuthResponse(number, password);
        String token = Objects.requireNonNull(authResponse.getBody()).getToken();
        headers.set("Authorization", token);

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        if (authResponse.getStatusCode().equals(HttpStatus.OK)) {
            ResponseEntity<BalanceDto> balanceResponse = restTemplate.exchange(BALANCE_URL,
                    HttpMethod.GET, jwtEntity, BalanceDto.class);
            balanceDto = balanceResponse.getBody();
        }
        return balanceDto;
    }

    private ResponseEntity<ResponseToken> getAuthResponse(String cardNumber, String cardPassword) {
        AuthenticationRequestDto authentication = new AuthenticationRequestDto(
                cardNumber, cardPassword);
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<AuthenticationRequestDto> authenticationEntity = new HttpEntity<>(authentication,
                authenticationHeaders);
        return restTemplate.exchange(AUTHORIZATION_URL,
                HttpMethod.POST, authenticationEntity, ResponseToken.class);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
