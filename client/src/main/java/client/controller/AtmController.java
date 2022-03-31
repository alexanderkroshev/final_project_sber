package client.controller;

import client.service.CardService;
import common.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class AtmController {
    private CardService cardService;

    @GetMapping("/balance")
    public BalanceDto getBalance() {
        return cardService.getBalance();
    }

    @PostMapping("/login/{number}/{password}")
    public void login(@PathVariable String number,@PathVariable String password) {
        cardService.login(number, password);
    }

    @GetMapping("/logout")
    public void logout() {
        cardService.logout();
    }
}

