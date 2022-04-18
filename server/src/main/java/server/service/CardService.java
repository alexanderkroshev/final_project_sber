package server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import server.dto.UserCardDto;
import server.exception.CardNotFoundException;
import server.exception.CardSaveException;
import server.model.Card;
import server.repository.CardRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public Card findByLogin(String cardNumber) {
        return cardRepository.findByLogin(cardNumber).orElseThrow(CardNotFoundException::new);
    }

    public void saveCard(String cardNumber, String cardPassword, Long userId) {
        if (!cardNumber.matches("[0-9]+"))
            throw new CardSaveException("card number should contain only numbers");
        if (cardPassword == null || userId == null)
            throw new CardSaveException("card should not have null values");
        try {
            cardRepository.saveCard(cardNumber, cardPassword, userId);
        } catch (Exception e) {
            throw new CardSaveException("problem with save card");
        }
    }

    public List<UserCardDto> findUserCards(Long userId) {
        return cardRepository.findUserCards(userId);
    }
}

