package server.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LegalEntity {
    private int id;
    private String fullName;
    private BigDecimal balance;
    private LocalDate date;

}
