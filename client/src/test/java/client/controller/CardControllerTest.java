package client.controller;

import client.service.CardService;
import common.dto.BalanceDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CardControllerTest {
    @MockBean
    private CardService cardService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void cardBalance() throws Exception {
        Mockito.when(cardService.getBalance()).thenReturn(new BalanceDto(new BigDecimal(100)));
        mockMvc.perform(get("/cards/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(100));
    }
}


