package server.model;

import lombok.*;
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

    //@Getter(AccessLevel.NONE)//TODO
    private String cardNumber;
    @Override
    public String getLogin() {
        return cardNumber;
    }

    //@Getter(AccessLevel.NONE)//TODO
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
