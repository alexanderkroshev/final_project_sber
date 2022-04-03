package server.controller;

import common.AuthenticationRequestDto;
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
        ResponseEntity<?> response = authController.login(authDto);

        Assertions.assertNotNull(response.getBody()!=null);


    }

    @Test
    void logout() {
    }
}