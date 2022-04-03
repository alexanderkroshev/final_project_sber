package server.controller;

import common.AuthenticationRequestDto;
import common.TokenDto;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import server.auth.jwt.JwtTokenProvider;
import server.model.Card;
import server.repository.CardRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
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
            Card card = cardRepository.findByCardNumber(authDto.getCardNumber());
            authManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authDto.getCardNumber(), authDto.getCardPassword()));
            String token = jwtTokenProvider.createToken(authDto.getCardNumber(), card.getCardRole().name());
            return ResponseEntity.ok(new TokenDto(token));
        } catch (Exception e) {
            log.info("fail of authentication");
            throw new RuntimeException("fail of authentication");
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
