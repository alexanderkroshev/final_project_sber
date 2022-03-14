package server.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class BalanceResponse {
    private final  BigDecimal balance;
}
