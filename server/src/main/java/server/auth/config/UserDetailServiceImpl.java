package server.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.model.BasicModel;
import server.service.CardService;
import server.service.UserService;

@Service("userDetailsServiceImpl")
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService  {
    private CardService cardService;
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        BasicModel model;
        if (login.matches("[0-9]+"))
            model = cardService.findByLogin(login);
        else
            model = userService.findByLogin(login);

        return UserDetailsImpl.fromBasicModel(model);
    }
}

