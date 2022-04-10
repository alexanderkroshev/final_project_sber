package server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.dto.UserCardDto;
import server.model.Card;
import server.repository.CardRepository;

import java.util.List;


@Slf4j
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

