package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.exception.UserNotFoundException;
import server.model.User;
import server.repository.UserRepository;


@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    public void saveUser(String login, String password, String name, String surname) {
        userRepository.saveUser(login, password, name, surname);
    }
}
