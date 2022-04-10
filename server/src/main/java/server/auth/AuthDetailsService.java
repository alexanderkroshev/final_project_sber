package server.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.model.BasicAuthModel;
import server.service.CardService;
import server.service.UserService;

@Service("AuthDetailsService")
@AllArgsConstructor
public class AuthDetailsService implements UserDetailsService  {
    private CardService cardService;
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        BasicAuthModel model;
        if (login.matches("[0-9]+"))
            model = cardService.findByLogin(login);
        else
            model = userService.findByLogin(login);
        return AuthDetails.fromBasicModel(model);
    }
}
