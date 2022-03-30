package client.controller;

import common.BalanceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class AtmControllerTest {

    @Autowired
    private AtmController atmController;

    @Test
    void getBalance() {
        BalanceDto balance = atmController.getBalance("12345242", "1111");
        BigDecimal bigDecimal = balance.getBalance();
        BigDecimal bigDecimal2 = new BigDecimal("100");

        Assertions.assertEquals(bigDecimal, bigDecimal2);
    }
}