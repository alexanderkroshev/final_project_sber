package server.controller;

import common.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.CardService;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {
    private CardService cardService;

    @GetMapping("/balance")
    @PreAuthorize("hasAuthority('developers:read')")
    public BalanceDto getBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new BalanceDto(cardService.findByCardNumber(authentication.getName()).getBalance());
    }
}
