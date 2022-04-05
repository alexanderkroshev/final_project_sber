package server.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.model.Card;

@Repository
@AllArgsConstructor
public class CardRepository {
    private JdbcTemplate jdbcTemplate;

    public Card findByCardNumber(String cardNumber) {
        return jdbcTemplate.queryForObject(
                "select * from card where login=?",
                new BeanPropertyRowMapper<>(Card.class),
                cardNumber);
    }
}

