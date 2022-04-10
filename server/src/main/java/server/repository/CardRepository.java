package server.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import server.dto.CardsBelongToUserDto;
import server.exception.CardNotFoundException;
import server.model.Card;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class CardRepository {
    private JdbcTemplate jdbcTemplate;

    public Card findByLogin(String cardNumber) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from card where card_number=?",
                    new BeanPropertyRowMapper<>(Card.class),
                    cardNumber
            );
        } catch (Exception e) {
            String msg = "card " + cardNumber + " not found";
            log.info(msg);
            throw new CardNotFoundException(msg);
        }
    }

    public Boolean saveCard(Card card) {
        String sql = "insert into card(card_number, card_password, user_id) values (?,?,?);";
        jdbcTemplate.update(sql, card.getLogin(), card.getPassword(), card.getUserId());
        return true;
    }

    public List<CardsBelongToUserDto> findCardsBelongToUser(Long id) {
        return jdbcTemplate.query(
                "select * from card where user_id=?",
                BeanPropertyRowMapper.newInstance(CardsBelongToUserDto.class),
                id
        );
    }
}