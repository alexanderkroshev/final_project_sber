package server.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.User;
import server.service.UserService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping ("/{login}")
    @PreAuthorize("hasAuthority('developers:write')")
    public User findUserByCardId(@PathVariable String login) {
        return userService.findByLogin(login);
    }



    //@PreAuthorize("hasAuthority('developers:write')")
    @GetMapping ("/all")
    @PreAuthorize("hasAuthority('developers:write')")
    public List<User> findAll() {
        return userService.findAll();
    }
}
