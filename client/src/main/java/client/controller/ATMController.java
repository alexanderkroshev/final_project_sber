package client.controller;

import client.dto.BalanceDTO;
import client.service.CardServiceApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ATMController {

 private CardServiceApi service;

    @GetMapping("/balance/{id}")
    public BalanceDTO getBalance(@PathVariable Long id) {
        return service.getBalance(id);
    }
}

