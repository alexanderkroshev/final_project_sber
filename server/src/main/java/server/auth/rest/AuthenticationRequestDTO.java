package server.auth.rest;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private final String cardNumber;
    private final String cardPassword;
}
