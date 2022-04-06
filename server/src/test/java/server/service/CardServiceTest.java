package server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.auth.Role;
import server.auth.Status;
import server.model.Card;
import server.repository.CardRepository;

import java.math.BigDecimal;

@SpringBootTest
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Test
    void findByCardNumber() {
        Card card1 = new Card(
                2L,
                "12345242",
                "1111",
                new BigDecimal(100),
                null,
                Role.USER,
                Status.ACTIVE
        );

        Mockito.when(cardRepository.findByLogin("12345242")).thenReturn(card1);
        Card card2 = cardRepository.findByLogin("12345242");

        Assertions.assertEquals(card1.getLogin(), card2.getLogin());
    }
}