package server.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.model.User;

import java.util.List;


@Repository
@AllArgsConstructor
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public User findByLogin(String login) {
        return jdbcTemplate.queryForObject(
                "select * from user where login=?",
                BeanPropertyRowMapper.newInstance(User.class),
                login
        );
    }

    public List<User> findAll() {
        return jdbcTemplate.queryForList("select * from user", User.class);
    }
}
