package server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.model.Card;
import server.repository.CardRepository;

@SpringBootTest
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Test
    void findByCardNumber() {
        Card card1 = new Card();
        card1.setId(2L);
        card1.setCardNumber("12345242");
        Mockito.when(cardRepository.findByCardNumber("12345242")).thenReturn(card1);
        Card card2 = cardRepository.findByCardNumber("12345242");

        Assertions.assertEquals(card1.getCardNumber(), card2.getCardNumber());
    }
}