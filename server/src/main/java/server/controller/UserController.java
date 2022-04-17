package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.model.User;
import server.service.UserService;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('write')")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname()
        );
    }
}
