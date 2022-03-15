package client.controller;

import client.dto.BalanceDTO;
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


    @GetMapping("/balance/{id}")
    public BalanceDTO getBalance(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/card/" + id;
        try {
            return restTemplate.getForObject(new URI(url), BalanceDTO.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

