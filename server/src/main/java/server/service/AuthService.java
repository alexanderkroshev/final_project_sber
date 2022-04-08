package server.service;

import common.AuthDto;
import common.TokenDto;
import common.Type;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import server.auth.jwt.JwtTokenProvider;
import server.exception.AuthException;
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
        try {
            if (authDto.getType().equals(Type.CARD))
                basicModel = cardRepository.findByLogin(authDto.getLogin());
            else
                basicModel = userRepository.findByLogin(authDto.getLogin());
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword())
            );
            String token = jwtTokenProvider.createToken(authDto.getLogin(), basicModel.getRole().name());
            return ResponseEntity.ok(new TokenDto(token));
        } catch (Exception e) {
            String msg = "auth failed for user: " + authDto.getLogin();
            log.info(msg);
            throw new AuthException(msg);
         //   throw new RuntimeException(msg); //TODO custom exception
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}

