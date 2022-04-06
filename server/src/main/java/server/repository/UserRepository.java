package server.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.model.User;


@Repository
@AllArgsConstructor
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public User findUserByCardId(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from user u inner join card c on u.id = c.id where c.id=?",
                BeanPropertyRowMapper.newInstance(User.class),
                id
        );
    }
}
