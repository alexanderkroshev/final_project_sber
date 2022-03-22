package server.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.dto.BalanceDTO;

import java.math.BigDecimal;

@SpringBootTest
class CardControllerTest {

    @Autowired
    private  CardController cardController;

    @Test
    void getBalance() {
        BalanceDTO balance = cardController.findBalanceById(3L);
        BigDecimal bigDecimal = balance.getBalance();
        BigDecimal bigDecimal2 = new BigDecimal("200.25");

        Assertions.assertEquals(bigDecimal, bigDecimal2);
    }
}