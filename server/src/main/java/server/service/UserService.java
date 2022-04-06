package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.model.User;
import server.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User findUserByCardId(Long id) {
        return userRepository.findUserByCardId(id);
    }
}
