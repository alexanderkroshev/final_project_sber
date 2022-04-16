package server.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import server.exception.UserNotFoundException;
import server.model.Card;
import server.model.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select * from user where login= :login")
    User findByLogin(@Param("login") String login);

   /* private JdbcTemplate jdbcTemplate;

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
    }*/
}


