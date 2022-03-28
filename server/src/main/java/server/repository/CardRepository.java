package server.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.dto.BalanceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@AllArgsConstructor
public class CardRepository {

    private JdbcTemplate jdbcTemplate;

    public BalanceDTO findBalanceById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT balance FROM card where id=?", this::mapRowToCard, id);
    }

    private BalanceDTO mapRowToCard(ResultSet rs, int rowNum) throws SQLException {
        return new BalanceDTO(rs.getBigDecimal("balance"));
    }

}
