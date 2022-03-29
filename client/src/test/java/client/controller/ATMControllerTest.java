package client.controller;

import client.dto.BalanceDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class ATMControllerTest {

    @Autowired
    private ATMController atmController;

//    @Test
//    void getBalance() {
//        BalanceDTO balance = atmController.getBalance(3L);
//        BigDecimal bigDecimal = balance.getBalance();
//        BigDecimal bigDecimal2 = new BigDecimal("200.25");
//
//        Assertions.assertEquals(bigDecimal, bigDecimal2);
//    }
}