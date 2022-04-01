package server.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.auth.Role;
import server.auth.Status;
import server.model.Card;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@AllArgsConstructor
public class CardRepository {
    private JdbcTemplate jdbcTemplate;

    public Card findByCardNumber(String cardNumber) {
        return jdbcTemplate.queryForObject(
                "select * from card where card_number=?", this::mapRowToCard, cardNumber);
    }

    private Card mapRowToCard(ResultSet rs, int rowNum) {
        try {
            return new Card(rs.getLong("id"),
                    rs.getString("card_number"),
                    rs.getString("card_password"),
                    rs.getBigDecimal("balance"),
                    Role.valueOf(rs.getString("card_role")),
                    Status.valueOf(rs.getString("status"))
            );
        } catch (SQLException e) {
            throw new RuntimeException("problem with parsing data from server, CardRepository.class");
        }
    }
}
