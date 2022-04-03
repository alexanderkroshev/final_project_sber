package server.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import server.model.Card;
import server.service.CardService;

import java.math.BigDecimal;

@SpringBootTest
class CardControllerTest {

    @Mock
    private CardService cardService;

    @Test
    void getBalance() {
        Card card1 = new Card();
        card1.setId(2L);
        card1.setCardNumber("12345242");
        card1.setBalance(new BigDecimal(100));

        Mockito.when(cardService.findByCardNumber("12345242")).thenReturn(card1);
        BigDecimal bigDecimal = cardService.findByCardNumber("12345242").getBalance();

        Assertions.assertEquals(bigDecimal, card1.getBalance());
    }
}
