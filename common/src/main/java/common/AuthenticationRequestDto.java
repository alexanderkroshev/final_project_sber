package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {
    private String login;
    private String password;
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
