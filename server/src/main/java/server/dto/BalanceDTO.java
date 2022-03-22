package server.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class BalanceDTO {
    private final BigDecimal balance;
}
