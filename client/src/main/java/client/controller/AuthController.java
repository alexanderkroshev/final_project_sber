package client.controller;

import client.service.AuthService;
import common.dto.AuthDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody AuthDto authDto) {
        authService.login(authDto.getLogin(), authDto.getPassword());
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
