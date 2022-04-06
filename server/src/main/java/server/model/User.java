package server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.auth.Role;
import server.auth.Status;

import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements BasicModel {
    @Id
    private Long id;

    private String login;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private Status status;
}
