package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.model.Card;
import server.repository.CardRepository;


@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public Card findByLogin(String cardNumber) {
        return cardRepository.findByLogin(cardNumber);
    }
}
