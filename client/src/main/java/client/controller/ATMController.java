package client.controller;

import client.auth.Card;
import client.auth.ResponseToken;
import client.dto.BalanceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@RestController
@AllArgsConstructor
public class ATMController {

    private static final String AUTHORIZATION_URL = "http://localhost:8083/api/v1/auth/login";
    private static final String BALANCE_URL = "http://localhost:8083/card/balance";

    @GetMapping("/balance/{number}/{password}")
    public BalanceDTO getBalance(@PathVariable String number, @PathVariable String password) {
        BalanceDTO balanceDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<ResponseToken> authResponse = getAuthResponse(number, password);
        String token = Objects.requireNonNull(authResponse.getBody()).getToken();
        headers.set("Authorization", token);

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        if (authResponse.getStatusCode().equals(HttpStatus.OK)) {
            ResponseEntity<BalanceDTO> balanceResponse = restTemplate.exchange(BALANCE_URL,
                    HttpMethod.GET, jwtEntity, BalanceDTO.class);
            balanceDTO = balanceResponse.getBody();
        }
        return balanceDTO;
    }

    private ResponseEntity<ResponseToken> getAuthResponse(String cardNumber, String cardPassword) {
        RestTemplate restTemplate = new RestTemplate();
        Card authenticationCard = new Card(cardNumber, cardPassword);
        String authenticationBody = getBody(authenticationCard);
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<String> authenticationEntity = new HttpEntity<>(authenticationBody,
                authenticationHeaders);
        return restTemplate.exchange(AUTHORIZATION_URL,
                HttpMethod.POST, authenticationEntity, ResponseToken.class);
    }

    private String getBody(Card card) {
        String body;
        try {
            body = new ObjectMapper().writeValueAsString(card);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return body;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}

