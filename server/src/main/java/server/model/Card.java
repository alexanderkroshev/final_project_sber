package server.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import server.auth.Role;
import server.auth.Status;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Card {
    private Long id;
    private String cardNumber;
    private String cardPassword;
    private BigDecimal balance;
    private Role cardRole;
    private Status status;
}
