package server.service;

import common.dto.AuthDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import server.exception.CardNotFoundException;

@SpringBootTest
class AuthDetailsServiceTest {
    @Autowired
    private AuthService authService;

    @Test
    void loginSucceed() {
        AuthDto authDto = new AuthDto("12345678", "1111");
        String response = authService.login(authDto).getBody().getToken();
        Assertions.assertNotNull(response);
    }

    @Test
    void cardNotFound() {
        Assertions.assertThrows(CardNotFoundException.class, () -> {
            AuthDto authDto = new AuthDto("1", "1111");
            authService.login(authDto).getBody().getToken();
        });
    }

    @Test
    void invalidPassword() {
        Assertions.assertThrows(BadCredentialsException.class, () -> {
            AuthDto authDto = new AuthDto("12345678", "3424234234");
            authService.login(authDto).getBody().getToken();
        });
    }

    @Test
    void blockedCard() {
        Assertions.assertThrows(LockedException.class, () -> {
            AuthDto authDto = new AuthDto("78861357", "1111");
            authService.login(authDto).getBody().getToken();
        });
    }
}
