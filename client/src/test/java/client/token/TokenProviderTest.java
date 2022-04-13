package client.token;

import client.exception.TokenNotFoundException;
import common.Type;
import common.dto.AuthDto;
import common.dto.TokenDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
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


    @Test
    void login() {
        ResponseEntity<TokenDto> response = new ResponseEntity<>(
                new TokenDto(token), HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                "http://localhost:8080/api/v1/auth/login",
                HttpMethod.POST,
                new HttpEntity<>(new AuthDto(login, password, Type.CARD)),
                TokenDto.class)).thenReturn(response);

        tokenProvider.login(login, password);

        Mockito.verify(restTemplate, Mockito.times(1)).exchange(
                "http://localhost:8080/api/v1/auth/login",
                HttpMethod.POST,
                new HttpEntity<>(new AuthDto(login, password, Type.CARD)),
                TokenDto.class);
        System.out.println(tokenProvider.getToken() + "************");///////

        Assertions.assertEquals(token, response.getBody().getToken());
        Assertions.assertEquals(token, tokenProvider.getToken());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        tokenProvider.logout();
        Mockito.verify(restTemplate, Mockito.times(1))
                .postForObject(
                        "http://localhost:8080/api/v1/auth/logout",
                        new HttpEntity<>(headers),
                        Void.class);

        Assertions.assertThrows(
                TokenNotFoundException.class,
                () -> tokenProvider.getToken()
        );
    }
}