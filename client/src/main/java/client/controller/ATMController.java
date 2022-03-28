package client.controller;

import client.dto.BalanceDTO;
import client.service.CardServiceApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
public class ATMController {

 private CardServiceApi service;

    @GetMapping("/balance/{id}")
    public BalanceDTO getBalance(@PathVariable Long id) {
        return service.getBalance(id);
    }
}

