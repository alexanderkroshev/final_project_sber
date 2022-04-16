package server.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import server.Role;
import server.Status;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card implements AuthModel {
    @Id
    private Long id;

    private String cardNumber;
    @Override
    public String getLogin() {
        return cardNumber;
    }

    private String cardPassword;
    @Override
    public String getPassword() {
        return cardPassword;
    }

    private BigDecimal balance;
    private Long userId;
    private final Role role = Role.USER;
    private Status status;


}
