package common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestDto {
    private String cardNumber;
    private String cardPassword;
}

//TODO
//{
//"type": "user"
//"login": "alex"
//"password": "123"
//}
//
//{
//"type": "card"
//"login": "123453543"
//"password": "1212"
//}


//interface Auth {
//    String getLogin();
//    String getPassword();
//}
//
//@RequiredArgsConstructor
//final class CardAuth implements Auth {
//
//    private final String cardNumber;
//    private final String cardPassword;
//
//    @Override
//    public String getLogin() {
//        return cardNumber;
//    }
//
//    @Override
//    public String getPassword() {
//        return cardPassword;
//    }
//}
//
//@RequiredArgsConstructor
//final class UserAuth implements Auth {
//
//    private final String user;
//    private final String password;
//
//    @Override
//    public String getLogin() {
//        return user;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//}
