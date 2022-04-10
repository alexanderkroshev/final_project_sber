package server.service;

import common.dto.AuthDto;
import common.dto.TokenDto;
import common.Type;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import server.auth.jwt.JwtTokenProvider;
import server.model.BasicAuthModel;
import server.repository.CardRepository;
import server.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authManager;
    private CardRepository cardRepository;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<TokenDto> login(AuthDto authDto) {
        BasicAuthModel basicModel;
        Type type = authDto.getType();
        String login = authDto.getLogin();
        if (type.equals(Type.CARD))
            basicModel = cardRepository.findByLogin(login);
        else
            basicModel = userRepository.findByLogin(login);
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, authDto.getPassword())
        );
        String token = jwtTokenProvider.createToken(login, basicModel.getRole().name());
        return ResponseEntity.ok(new TokenDto(token));
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}

