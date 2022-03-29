package server.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.model.Card;
import server.service.CardService;


@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class CardDetailServiceImpl implements UserDetailsService {

    private final CardService cardService;

    @Override
    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {
        Card card = cardService.findByCardNumber(cardNumber);
        return SecurityCard.fromCard(card);
    }
}

