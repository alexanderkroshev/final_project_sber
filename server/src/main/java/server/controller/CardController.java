package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.dto.BalanceDTO;
import server.service.CardService;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {

    private CardService cardService;

//    @GetMapping("balance/{id}")
//    public BalanceDTO findBalanceById(@PathVariable Long id) {
//        return cardService.findBalanceById(id);
//    }

    @GetMapping("/balance")
    @PreAuthorize("hasAuthority('developers:read')")
    public BalanceDTO getBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new BalanceDTO(cardService.findByCardNumber(authentication.getName()).getBalance());
    }
}
