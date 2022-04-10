package server.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.exception.UserNotFoundException;
import server.model.User;


@Slf4j
@Repository
@AllArgsConstructor
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public User findByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from user where login=?",
                    BeanPropertyRowMapper.newInstance(User.class),
                    login
            );
        } catch (Exception e) {
            String msg = "user " + login + " not found";
            log.info(msg);
            throw new UserNotFoundException(msg);
        }
    }

    public void saveUser(User user) {
            String sql = "insert into user(login, password, name, surname) values (?,?,?,?);";
            jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
    }
}


