package server.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import server.dto.UserCardDto;
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

    @Modifying
    @Query("insert into card(card_number, card_password, user_id)" +
            "values (:card_number,:card_password, :user_id)")
    void saveCard(
            @Param("card_number") String cardNumber,
            @Param("card_password") String cardPassword,
            @Param("user_id") Long id
    );


    //TODO UserCardDto


//TODO
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
