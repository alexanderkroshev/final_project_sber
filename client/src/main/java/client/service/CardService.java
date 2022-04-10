package client.service;

import client.exception.BalanceNotFoundException;
import client.token.TokenProvider;
import common.dto.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
    private TokenProvider tokenProvider;
    private RestTemplate restTemplate;
    private final String BALANCE_URL = "http://localhost:8080/cards/balance";

    public BalanceDto getBalance() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenProvider.getToken());
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<BalanceDto> balanceResponse = restTemplate.exchange(BALANCE_URL,
                HttpMethod.GET, httpEntity, BalanceDto.class);
        BalanceDto balanceDto = balanceResponse.getBody();
        return Optional.ofNullable(balanceDto).
                orElseThrow(BalanceNotFoundException::new);
    }
}
