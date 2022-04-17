package server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.dto.UserCardDto;
import server.exception.CardNotFoundException;
import server.exception.UserNotFoundException;
import server.model.Card;

import java.util.List;

@SpringBootTest
class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @Test
    void findByCardNumber() {
        Card card1 = new Card();
        card1.setCardNumber("12345242");
        Card card2 = cardRepository.findByLogin("12345242")
                .orElseThrow(CardNotFoundException::new);
        Assertions.assertEquals(card1.getLogin(), card2.getLogin());
    }

    @Test
    void saveCard() {
        String cardNumber = "1212232343";
        cardRepository.saveCard(cardNumber, "1111", 1L);
        Card card2 = cardRepository.findByLogin(cardNumber)
                .orElseThrow(UserNotFoundException::new);
        Assertions.assertEquals(card2.getLogin(), cardNumber);
    }

    @Test
    void findUserCards() {
        List<UserCardDto> cards = cardRepository.findUserCards(3L);
        Assertions.assertEquals(2, cards.size());
    }
}
