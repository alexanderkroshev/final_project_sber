package server.auth.authDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class UserAuthDto implements Auth {

    private final String user;
    private final String password;

    @Override
    public String getLogin() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
