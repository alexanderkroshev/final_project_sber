package server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.dto.UserCardDto;
import server.exception.CardNotFoundException;
import server.model.Card;
import java.util.List;


@SpringBootTest
class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @Test
    void findByCardNumberSucceed() {
        Card card1 = new Card();
        card1.setCardNumber("12345242");
        Card card2 = cardRepository.findByLogin("12345242");
        Assertions.assertEquals(card1.getLogin(), card2.getLogin());
    }

    @Test
    void findByCardNumberFailed() {
        Assertions.assertThrows(CardNotFoundException.class,
                () -> cardRepository.findByLogin("11133"));
    }

    @Test
    void saveCard() {
        Card card1 = new Card();
        card1.setCardNumber("88888888");
        card1.setCardPassword("1111");
        card1.setUserId(3L);
        cardRepository.saveCard(card1);
        Card card2 = cardRepository.findByLogin("88888888");
        Assertions.assertEquals(card2.getLogin(), card1.getLogin());
    }

    @Test
    void findUserCards() {
        List<UserCardDto> cards = cardRepository.findUserCards(3L);
        Assertions.assertEquals(3, cards.size());
    }
}
