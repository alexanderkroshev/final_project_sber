package server.controller;

import common.dto.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import server.dto.CardsBelongToUserDto;
import server.model.Card;
import server.model.User;
import server.service.CardService;
import server.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private CardService cardService;
    private UserService userService;

    @GetMapping("/balance")
    @PreAuthorize("hasAuthority('developers:read')")
    public BalanceDto getBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Card card = cardService.findByLogin(authentication.getName());
        return new BalanceDto(card.getBalance());
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('developers:write')")
    public boolean saveCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @GetMapping("/belong_to_user")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<CardsBelongToUserDto> findCardsBelongToUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(authentication.getName());
        return cardService.findCardsBelongToUser(user.getId());
    }
}
