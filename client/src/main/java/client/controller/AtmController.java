package client.controller;

import client.service.CardService;
import common.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class AtmController {
    private final CardService cardService;

    @GetMapping("/balance/{number}/{password}")
    public BalanceDto getBalance(@PathVariable String number, @PathVariable String password) {
        return cardService.getBalance(number, password);
    }
}

