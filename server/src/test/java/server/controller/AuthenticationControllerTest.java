package server.controller;

import common.AuthenticationRequestDto;
import common.TokenDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

class AuthenticationControllerTest {

    @Autowired
    private AuthenticationController authController;

    @Test
    void login() {
        AuthenticationRequestDto authDto = new AuthenticationRequestDto(
                "12345678", "1111");
        ResponseEntity<TokenDto> response = authController.login(authDto);

       //Assertions.assertTrue();


    }

    @Test
    void logout() {
    }
}