package server.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.model.Card;
import server.service.CardService;


@Service("userDetailsServiceImpl")
@AllArgsConstructor
public class CardDetailServiceImpl implements UserDetailsService {
    private CardService cardService;

    @Override
    public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {
        Card card = cardService.findByCardNumber(cardNumber);
        return CardDetails.fromCard(card);
    }
}

