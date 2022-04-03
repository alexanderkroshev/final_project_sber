package client.service;

import client.token.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {
    private TokenProvider tokenProvider;

    public void login(String number, String password) {
        tokenProvider.login(number, password);
    }

    public void logout() {
        tokenProvider.logout();
    }
}

