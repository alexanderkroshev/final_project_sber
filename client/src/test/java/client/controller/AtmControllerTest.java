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

    @Autowired
    private AuthController authController;


    @Test
    void getBalance() {
        authController.login("12345678", "1111");
        BalanceDto balance = atmController.getBalance();
        BigDecimal bigDecimal = balance.getBalance();
        BigDecimal bigDecimal2 = new BigDecimal("10");

        Assertions.assertEquals(bigDecimal, bigDecimal2);
    }
}