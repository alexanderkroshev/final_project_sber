package server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import server.Role;
import server.Status;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements AuthModel {
    @Id
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private Status status;
}
