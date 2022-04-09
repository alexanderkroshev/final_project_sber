package server.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import server.service.AuthService;

@SpringBootTest
class AuthControllerTest {
    @Mock
    private AuthService authService;

//TODO tests
//1. случай что все ок и вернулся токен
//2. случай что карта не найдена
//3. случай что пароль неправильный
//4. случай что если карта забанена, то токен не вернется и будет ошибка
    @Test
    void successfulLogin() {
//        AuthDto authDto = new AuthDto(    "12345678", "1111", Type.CARD);
//        Mockito.when(authService.login(authDto)).thenReturn();
//        ResponseEntity<TokenDto> response = authService.login(authDto);
    }



}