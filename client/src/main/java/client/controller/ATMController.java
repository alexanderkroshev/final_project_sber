package client.controller;

import client.dto.BalanceDTO;
import client.service.CardServiceApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class ATMController {

    private final CardServiceApi cardService;

    @GetMapping("/balance/{number}/{password}")
    public BalanceDTO getBalance(@PathVariable String number, @PathVariable String password) {
        return cardService.getBalance(number, password);
    }
}

