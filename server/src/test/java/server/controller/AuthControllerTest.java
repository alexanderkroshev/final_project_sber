package server.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {
//TODO tests
//1. случай что все ок и вернулся токен
//2. случай что карта не найдена
//3. случай что пароль неправильный
//4. случай что если карта забанена, то токен не вернется и будет ошибка
    @Test
    void successfulLogin() {
//        AuthenticationRequestDto authDto = new AuthenticationRequestDto(
//                "12345678", "1111");
//        ResponseEntity<TokenDto> response = authController.login(authDto);
//

    }

    @Test
    void cardNotFound() {

    }

    @Test
    void invalidPassword() {

    }

    @Test
    void blockedCard() {

    }

    @Test
    void adminAccess() {

    }

}