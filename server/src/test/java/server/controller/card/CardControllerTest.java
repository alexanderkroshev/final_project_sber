package server.controller.card;

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
import server.dto.UserCardDto;
import server.model.Card;
import server.model.User;
import server.repository.CardRepository;

import server.service.CardService;
import server.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private CardRepository cardRepository;
    @MockBean
    private UserService userService;

    private final AuthDto authCardDto = new AuthDto("12345678",
            "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu",
            Type.PERSON);

    private final AuthDto authUserDto = new AuthDto("user",
            "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu",
            Type.PERSON);

    private final String token = "token_123";

    private final Card card = new Card(1L,
            authCardDto.getLogin(),
            authCardDto.getPassword(),
            new BigDecimal(100),
            1L,
            Status.ACTIVE);

    @BeforeEach
    void setUpMocks() {
        Mockito.when(jwtTokenProvider.resolveToken(Mockito.any())).thenReturn(token);
        Mockito.when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        Mockito.when(jwtTokenProvider.getAuthentication(token)).thenReturn(
                new UsernamePasswordAuthenticationToken(authCardDto.getLogin(), "1111"));
        Mockito.when(cardService.findByLogin(Mockito.any())).thenReturn(card);
        Mockito.when(userService.findByLogin(Mockito.any())).thenReturn(
                new User(1L, authUserDto.getLogin(), authUserDto.getPassword(),
                        "bob", "", Role.USER, Status.ACTIVE));

        List<UserCardDto> cards = new ArrayList<>();
        cards.add(new UserCardDto("112323", new BigDecimal(100), Status.ACTIVE));
        cards.add(new UserCardDto("112342334", new BigDecimal(123), Status.ACTIVE));
        Mockito.when(cardService.findUserCards(Mockito.anyLong())).thenReturn(cards);
    }

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(
                        get("/cards/balance")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.balance").value(100));
    }

    @Test
    void cardList() throws Exception {
        mockMvc.perform(
                        get("/cards")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.length()").value(2));
    }
}
