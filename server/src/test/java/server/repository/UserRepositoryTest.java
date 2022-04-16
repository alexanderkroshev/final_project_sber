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
    void findByLoginSucceed() {
        User user = userRepository.findByLogin("Bob_1").orElseThrow(UserNotFoundException::new);
        long id = user.getId();
        Assertions.assertEquals(id, 1);
    }

//    @Test
//    void findByLoginFailed() {
//        Assertions.assertThrows(UserNotFoundException.class, () -> userRepository.findByLogin("Bob_2"));
//    }

//    @Test
//    void saveUser() {
//        User user1 = new User();
//        user1.setLogin("test");
//        user1.setPassword("1111");
//        user1.setName("test");
//        user1.setSurname("test");
//        userRepository.saveUser(user1);
//        User user2 = userRepository.findByLogin("test");
//        Assertions.assertEquals(user1.getLogin(), user2.getLogin());
//    }
}
