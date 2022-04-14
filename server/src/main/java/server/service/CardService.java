package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.dto.UserCardDto;
import server.model.Card;
import server.repository.CardRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public Card findByLogin(String cardNumber) {
        return cardRepository.findByLogin(cardNumber);
    }

    public boolean saveCard(Card card) {
        return cardRepository.saveCard(card);
    }

    public List<UserCardDto> findUserCards(Long userId) {
        return cardRepository.findUserCards(userId);
    }
}

