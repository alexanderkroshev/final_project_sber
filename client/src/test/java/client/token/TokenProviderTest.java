package client.token;

import client.exception.TokenNotFoundException;
import common.dto.TokenDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class TokenProviderTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private TokenProvider tokenProvider;

    private final String token = "23r34g345ygh54yhg54yh";
    private final String login = "41234234";
    private final String password = "1111";

    ResponseEntity<TokenDto> response = new ResponseEntity<>(
            new TokenDto(token), HttpStatus.OK);

    @BeforeEach
    void setUpMock() {
        Mockito.when(restTemplate.exchange(
                Mockito.any(String.class),
                Mockito.any(HttpMethod.class),
                Mockito.any(HttpEntity.class),
                Mockito.eq(TokenDto.class)
        )).thenReturn(response);
    }

    @Test
    void login() {
        tokenProvider.login(login, password);

        Mockito.verify(restTemplate, Mockito.times(1)).exchange(
                Mockito.any(String.class),
                Mockito.any(HttpMethod.class),
                Mockito.any(HttpEntity.class),
                Mockito.eq(TokenDto.class)
        );

        Assertions.assertEquals(token, tokenProvider.getToken());
    }

    @Test
    void logout() {
        tokenProvider.logout();

        Mockito.verify(restTemplate, Mockito.times(1))
                .postForObject(
                        Mockito.any(String.class),
                        Mockito.any(HttpEntity.class),
                        Mockito.eq(Void.class));

        Assertions.assertThrows(
                TokenNotFoundException.class,
                () -> tokenProvider.getToken()
        );
    }
}