package client.controller;

import client.auth.Card;
import client.auth.ResponseToken;
import client.dto.BalanceDTO;
import client.service.CardServiceApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class ATMController {

    private final CardServiceApi cardService;

    @GetMapping("/balance/{number}/{password}")
    public BalanceDTO getBalance(@PathVariable String number,@PathVariable String password) {
        return cardService.getBalance(number, password);
    }
}

