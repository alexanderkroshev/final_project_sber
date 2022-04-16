package server.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import server.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select * from user where login= :login")
    Optional<User> findByLogin(@Param("login") String login);

    @Modifying
    @Query("insert into user(login, password, name, surname)" +
            "values (:login, :password, :name, :surname)")
    void saveUser(
            @Param("login") String login,
            @Param("password") String password,
            @Param("name") String name,
            @Param("surname") String surname
    );
}


