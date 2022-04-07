package server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.auth.Role;
import server.auth.Status;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Auth{
    private Long id;
    private String login;
    private String password;
    private Set<Card> cards;
    private Role cardRole;
    private Status status;
}
