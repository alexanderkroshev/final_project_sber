package server.controller;

import common.AuthDto;
import common.TokenDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
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
//TODO main
//1. роль не должна быть частью карточки, это должна отдельная таблица с админом и юзерами

//TODO tests
//1. случай что все ок и вернулся токен
//2. случай что карта не найдена
//3. случай что пароль неправильный
//4. случай что если карта забанена, то токен не вернется и будет ошибка