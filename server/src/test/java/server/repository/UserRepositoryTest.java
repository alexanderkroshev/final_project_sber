package server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.exception.UserNotFoundException;
import server.model.User;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByLogin() {
        User user = userRepository.findByLogin("Bob_1").orElseThrow(UserNotFoundException::new);
        long id = user.getId();
        Assertions.assertEquals(id, 1);
    }

    @Test
    void saveUser() {
        userRepository.saveUser("test", "1111", "test", "test");
        User user = userRepository.findByLogin("test").orElseThrow(UserNotFoundException::new);
        Assertions.assertEquals("test", user.getLogin());
    }
}
