package server.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import server.service.CardService;
import server.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
class CardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;
    @MockBean
    private UserService userService;

//    @Test
//    void getBalance() throws Exception {
//        mockMvc.perform(get("cards/balance")).andExpect(status().isOk());
//      //  Mockito.verify()
//    }


//    @Test
//    void saveCard() throws Exception {
//        mockMvc.perform(post("/persons/new_person").param("nickname", "Bob").
//                        param("name", "Bobislav").param("password", "3Pdfdfs").
//                        param("roles", "admin")).
//                andExpect(status().isOk());)
//        verify(service, times(1)).findRoleByName(any();
//        verify(service, times(1)).savePerson(any());
//        verifyNoMoreInteractions(service);


//
//    @Test
//    void findCardsBelongToUser() {
//        Card card1 = new Card();
//        card1.setId(2L);
//        card1.setCardNumber("12345242");
//        card1.setBalance(new BigDecimal("100"));
//        Mockito.when(cardService.findByLogin("12345242")).thenReturn(card1);
//        BigDecimal bigDecimal = cardService.findByLogin("12345242").getBalance();
//        Assertions.assertEquals(bigDecimal, card1.getBalance());
//    }


}
