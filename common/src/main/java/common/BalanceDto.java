package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BalanceDto {
    private  BigDecimal balance;
}
