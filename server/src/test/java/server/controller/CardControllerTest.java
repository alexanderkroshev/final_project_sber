package server.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import server.auth.Role;
import server.auth.Status;
import server.model.Card;
import server.service.CardService;

import java.math.BigDecimal;

@SpringBootTest
class CardControllerTest {

    @Mock
    private CardService cardService;

    @Test
    void getBalance() {
        Card card1 = new Card(
                2L,
                "12345242",
                "1111",
                new BigDecimal(100),
                null,
                Role.USER,
                Status.ACTIVE
        );

        Mockito.when(cardService.findByLogin("12345242")).thenReturn(card1);
        BigDecimal bigDecimal = cardService.findByLogin("12345242").getBalance();

        Assertions.assertEquals(bigDecimal, card1.getBalance());
    }
}
