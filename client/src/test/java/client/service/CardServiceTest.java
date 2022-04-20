package client.service;

import client.token.TokenProvider;
import common.dto.BalanceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@SpringBootTest
class CardServiceTest {
    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CardService cardService;

    private String token = "23r34g345ygh54yhg54yh";

    @BeforeEach
    void setUpMocks() {
        Mockito.when(tokenProvider.getToken()).thenReturn(token);
    }

    @Test
    void getBalance() {
        BalanceDto balanceDto = new BalanceDto(new BigDecimal(100));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenProvider.getToken());
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        Mockito.when(restTemplate.exchange(
                        "http://localhost:8080/cards/balance",
                        HttpMethod.GET,
                        httpEntity,
                        BalanceDto.class))
                .thenReturn(new ResponseEntity<>(balanceDto, HttpStatus.OK));
        Assertions.assertEquals(cardService.getBalance(), balanceDto);
    }
}
