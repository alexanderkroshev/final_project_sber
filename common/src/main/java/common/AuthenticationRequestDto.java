package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationRequestDto {
    private final String cardNumber;
    private final String cardPassword;
}
