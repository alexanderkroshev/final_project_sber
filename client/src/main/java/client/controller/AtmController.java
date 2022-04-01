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
}

