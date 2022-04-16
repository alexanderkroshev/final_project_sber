package server.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import server.dto.CardDto;
import server.dto.UserCardDto;
import server.exception.CardNotFoundException;
import server.model.Card;

import java.util.List;
import java.util.Optional;


public interface CardRepository extends CrudRepository<Card, Long> {

    @Query("select * from card where card_number= :card_number")
    Optional<Card> findByLogin(@Param("card_number") String cardNumber);

//    @Modifying
//    @Query("insert into card(card_number, card_password, user_id) values (?,?,?)")
//    Card saveCard(@Param("CardNumber") String cardNumber);

    @Query("select * from card where user_id= :user_id")
    List<UserCardDto> findUserCards(@Param("user_id") Long userId);
    //TODO UserCardDto



    /*
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

    public List<UserCardDto> findUserCards(Long id) {
        return jdbcTemplate.query(
                "select * from card where user_id=?",
                BeanPropertyRowMapper.newInstance(UserCardDto.class),
                id
        );
    }*/
}
