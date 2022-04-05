package common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class CardAuthDto implements Auth {

    private final String cardNumber;
    private final String cardPassword;

    @Override
    public String getLogin() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
