package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.exception.CardSaveException;
import server.exception.UserNotFoundException;
import server.exception.UserSaveException;
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
        if (login.matches("[0-9]+"))
            throw new UserSaveException("user login should not contain only numbers");
        if (login == null || password == null || name == null || surname == null)
            throw new UserSaveException("user should not have null values");
        try {
            userRepository.saveUser(login, password, name, surname);
        } catch (Exception e) {
            throw new UserSaveException("problem with save user");
        }
    }
}
