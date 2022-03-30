package common;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private final String cardNumber;
    private final String cardPassword;
}
