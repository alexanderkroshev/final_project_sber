package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthenticationRequestDto  {
    private String login;
    private String password;

//    @Override
//    public String getLogin() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
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
