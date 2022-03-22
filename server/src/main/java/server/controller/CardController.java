package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.dto.BalanceDTO;
import server.service.CardService;

@RestController
@AllArgsConstructor
public class CardController {

    private CardService cardService;

    @GetMapping("/{id}")
    public BalanceDTO findBalanceById(@PathVariable Long id) {
        return cardService.findBalanceById(id);
    }
}
