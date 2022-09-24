package server.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.dto.LegalEntityDto;
import server.exceptions.LegalEntityNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class LegalEntityRepository {

    private JdbcTemplate jdbcTemplate;

    public List<LegalEntityDto> findAll() {
        return jdbcTemplate.query(
                "SELECT full_name, balance FROM legal_entity", this::mapRowToLegalEntity);
    }

    public LegalEntityDto findById(long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT full_name, balance FROM legal_entity where id =?", this::mapRowToLegalEntity, id);
        } catch (Exception e) {
            throw new LegalEntityNotFoundException("legal entity not found with id = " + id);
        }
    }

    private LegalEntityDto mapRowToLegalEntity(ResultSet rs, int rowNum) throws SQLException {
        return new LegalEntityDto(
                rs.getString("full_name"),
                rs.getBigDecimal("balance")
        );
    }
}