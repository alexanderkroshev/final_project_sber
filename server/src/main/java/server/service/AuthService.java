package server.service;

import common.dto.AuthDto;
import common.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import server.auth.jwt.JwtTokenProvider;
import server.model.AuthModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authManager;
    private CardService cardService;
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<TokenDto> login(AuthDto authDto) {
        AuthModel authModel;
        String login = authDto.getLogin();
        if (login.matches("[0-9]+"))
            authModel = cardService.findByLogin(login);
        else
            authModel = userService.findByLogin(login);
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, authDto.getPassword()));
        String token = jwtTokenProvider.createToken(login, authModel.getRole().name());
        return ResponseEntity.ok(new TokenDto(token));
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}

