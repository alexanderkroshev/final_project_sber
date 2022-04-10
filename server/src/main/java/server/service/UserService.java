package server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.model.User;
import server.repository.UserRepository;


@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }
}
