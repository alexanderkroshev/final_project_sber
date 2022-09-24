package server.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LegalEntityDto {
    private String fullName;
    private BigDecimal balance;
}