package server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import server.dto.CardsBelongToUserDto;
import server.model.Card;
import server.model.User;
import server.service.UserService;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('developers:write')")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
