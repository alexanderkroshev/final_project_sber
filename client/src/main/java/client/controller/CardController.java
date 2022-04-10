package client.controller;

import client.service.CardService;
import common.dto.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private CardService cardService;

    @GetMapping("/balance")
    public BalanceDto getBalance() {
        return cardService.getBalance();
    }
}
