package server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import server.auth.Role;
import server.auth.Status;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Card {
    private Long id;
    private String cardNumber;
    private String cardPassword;
    private BigDecimal balance;
    private Role cardRole;
    private Status status;
}
