package server.controller;

import common.dto.AuthDto;
import common.dto.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthDto authDto) {
        return authService.login(authDto);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
      authService.logout(request, response);
    }
}
