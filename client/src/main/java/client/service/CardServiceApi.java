package client.service;

import client.dto.BalanceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class CardServiceApi {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/card/";

    public BalanceDTO getBalance(Long id) {
        String url = BASE_URL + id;
        try {
            return restTemplate.getForObject(new URI(url), BalanceDTO.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
