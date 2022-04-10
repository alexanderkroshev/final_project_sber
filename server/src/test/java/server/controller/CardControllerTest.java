package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Type;
import common.dto.AuthDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import server.Role;
import server.Status;
import server.auth.jwt.JwtTokenProvider;
import server.model.Card;
import server.model.User;
import server.service.CardService;
import server.service.UserService;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private String token = "token_123";

    private AuthDto authCardDto = new AuthDto("bob", "1111", Type.PERSON);

    @BeforeEach
    void setUpMocks() {
        Mockito.when(cardService.findByLogin("12345678")).thenReturn(
                new Card(
                        1L,
                        "12345678",
                        "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu",
                        new BigDecimal("100"),
                        1L,
                        Status.ACTIVE
                )
        );
        Mockito.when(userService.findByLogin("Igor_4")).thenReturn(
                new User(
                        1L,
                        "Igor_4",
                        "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu",
                        "efse",
                        "efse",
                        Role.ADMIN,
                        Status.ACTIVE


        ));
        Mockito.when(jwtTokenProvider.resolveToken(Mockito.any())).thenReturn(token);
        Mockito.when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        Mockito.when(jwtTokenProvider.getAuthentication(token)).thenReturn(
                new UsernamePasswordAuthenticationToken(authCardDto.getLogin(), authCardDto.getPassword())
        );
    }

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(
                get("/cards/balance")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.balance").value(100));
    }

    @Test
    void saveCard() throws Exception {
        Card card = new Card();
        card.setCardNumber("111123234234");
        card.setCardPassword("1111");
        card.setUserId(1L);
        String json = new ObjectMapper().writeValueAsString(card);

        mockMvc.perform(
                post("/cards/create")
                        .header("Authorization", token)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
/*
        User user = new User();
        user.setId(1L);
        user.setLogin("bob");
        user.setPassword("1111");
        user.setName("test");
        user.setSurname("test");
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.ADMIN);
        */