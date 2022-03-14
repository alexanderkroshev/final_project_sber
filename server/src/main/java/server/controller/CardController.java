package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.messages.BalanceResponse;
import server.service.CardService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {

    private CardService cardService;

    @GetMapping("/{id}")
    public BalanceResponse getBalance(@PathVariable("id") Long id) {
        BigDecimal bigDecimal = cardService.findById(id).getBalance();
        return new BalanceResponse(bigDecimal);
    }

}
