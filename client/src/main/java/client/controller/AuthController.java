package client.controller;

import client.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import request.LoginRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        authService.login(loginRequest.getCardNumber(), loginRequest.getCardPassword());
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
