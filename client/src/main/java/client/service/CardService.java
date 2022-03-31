package client.service;

import client.savetoken.TokenProvider;
import common.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CardService {
    private TokenProvider tokenProvider;
    private RestTemplate restTemplate;
    private final String BALANCE_URL = "http://localhost:8080/card/balance";

    public BalanceDto getBalance() {
        BalanceDto balanceDto = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenProvider.getToken());
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<BalanceDto> balanceResponse = restTemplate.exchange(BALANCE_URL,
                HttpMethod.GET, jwtEntity, BalanceDto.class);
        balanceDto = balanceResponse.getBody();
        return balanceDto;
    }

    public void login(String number, String password) {
        tokenProvider.login(number, password);
    }

    public void logout() {
        tokenProvider.logout();
    }
}
