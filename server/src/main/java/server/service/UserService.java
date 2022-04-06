package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.model.User;
import server.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
