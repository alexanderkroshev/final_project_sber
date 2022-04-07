package server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import server.Role;
import server.Status;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card implements BasicAuthModel {
    @Id
    private Long id;

    private String cardNumber;
    private String cardPassword;
    private BigDecimal balance;
    private User user;
    private Role role = Role.USER;
    private Status status;

    @Override
    public String getLogin() {
        return cardNumber;
    }

    @Override
    public String getPassword() {
        return cardPassword;
    }
}
