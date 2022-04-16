package server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.dto.UserCardDto;
import server.exception.CardNotFoundException;
import server.model.Card;
import server.repository.CardRepository;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public Card findByLogin(String cardNumber)  {
        return cardRepository.findByLogin(cardNumber).orElseThrow(CardNotFoundException::new);
    }

    public void saveCard(String cardNumber, String cardPassword, Long userId) {
        cardRepository.saveCard(cardNumber, cardPassword, userId);
    }

    public List<UserCardDto> findUserCards(Long userId) {
        return cardRepository.findUserCards(userId);
    }
}

