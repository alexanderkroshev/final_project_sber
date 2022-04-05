package server.controller;

import common.Auth;
import common.AuthenticationRequestDto;
import common.TokenDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.auth.jwt.JwtTokenProvider;
import server.model.Card;
import server.repository.CardRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationManager authManager;
    private CardRepository cardRepository;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthenticationRequestDto authDto) {
        try {
            Card card = cardRepository.findByCardNumber(authDto.getLogin());
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword())
            );
            String token = jwtTokenProvider.createToken(authDto.getLogin(), card.getCardRole().name());
            return ResponseEntity.ok(new TokenDto(token));
        } catch (Exception e) {
            String msg = "auth failed for user: " + authDto.getLogin();
            log.info(msg);
            throw new RuntimeException(msg); //TODO custom exception
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}

//TODO main
//1. роль не должна быть частью карточки, это должна отдельная таблица с админом и юзерами

//TODO tests
//1. случай что все ок и вернулся токен
//2. случай что карта не найдена
//3. случай что пароль неправильный
//4. случай что если карта забанена, то токен не вернется и будет ошибка