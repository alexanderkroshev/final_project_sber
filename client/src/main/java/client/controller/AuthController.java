package client.controller;

import client.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login/{number}/{password}")
    public void login(@PathVariable String number, @PathVariable String password) {
        authService.login(number, password);
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
