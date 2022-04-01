package client.controller;

import client.exception.AuthorizationException;
import client.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class AuthenticationController  {
    private CardService cardService;

    @PostMapping("/login/{number}/{password}")
    public void login(@PathVariable String number, @PathVariable String password) {
        cardService.login(number, password);
       // log.info("incorrect login/password");
    }

    @PostMapping("/logout")
    public void logout() {
        cardService.logout();
    }
}

