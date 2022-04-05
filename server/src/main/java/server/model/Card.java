package server.model;

import common.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.auth.Role;
import server.auth.Status;


import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Auth {
    private Long id;
    private String login;
    private String password;
    private BigDecimal balance;
    private Role cardRole = Role.USER;
    private Status status;
}
