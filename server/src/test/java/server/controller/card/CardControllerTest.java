package server.controller.card;

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
import server.dto.UserCardDto;
import server.model.Card;
import server.model.User;
import server.repository.CardRepository;
import server.repository.UserRepository;
import server.service.CardService;
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
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper mapper;

    private final AuthDto authDto = new AuthDto("12345678", "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu", Type.PERSON);
    private final AuthDto authAdminDto = new AuthDto("admin", "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu", Type.PERSON);

    private final String token = "token_123";

    private final Card card = new Card(1L,
            authDto.getLogin(),
            authDto.getPassword(),
            new BigDecimal(100),
            1L,
            Status.ACTIVE);

    private final Card saveCard = new Card(2L,
            "1231234234234",
            "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu",
            new BigDecimal(100),
            2L,
            Status.ACTIVE);

    @BeforeEach
    void setUpMocks() {
        Mockito.when(jwtTokenProvider.resolveToken(Mockito.any())).thenReturn(token);
        Mockito.when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        Mockito.when(jwtTokenProvider.getAuthentication(token)).thenReturn(
                new UsernamePasswordAuthenticationToken(authDto.getLogin(), "1111")
        );
        Mockito.when(cardService.findByLogin(Mockito.any())).thenReturn(card);
        Mockito.when(cardRepository.findByLogin(Mockito.any())).thenReturn(card);
        Mockito.when(userRepository.findByLogin(Mockito.any())).thenReturn(
                new User(1L, authAdminDto.getLogin(),
                        authAdminDto.getPassword(),
                        "bob", "", Role.USER, Status.ACTIVE));

        List<UserCardDto> cards = new ArrayList<>();
        UserCardDto userCardDto1 = new UserCardDto("112323", new BigDecimal(100), Status.ACTIVE);
        UserCardDto userCardDto2 = new UserCardDto("112342334", new BigDecimal(123), Status.ACTIVE);
        cards.add(userCardDto1);
        cards.add(userCardDto2);
        Mockito.when(cardService.findUserCards(Mockito.anyLong())).
                thenReturn(cards);
    }

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(
                        get("/cards/balance")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(100));
    }

    @Test
    void cardList() throws Exception {
        mockMvc.perform(
                        get("/cards")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
