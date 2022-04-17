package server.controller.card;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import server.service.UserService;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CardControllerSaveTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper mapper;

    private final AuthDto authAdminDto = new AuthDto(
            "admin",
            "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu"
    );

    private final String token = "token_123";

    private final Card saveCard = new Card(
            2L,
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
                new UsernamePasswordAuthenticationToken(authAdminDto.getLogin(), "1111"));
        Mockito.when(userService.findByLogin(Mockito.any())).thenReturn(
                new User(1L, authAdminDto.getLogin(), authAdminDto.getPassword(),
                        "bob", "", Role.ADMIN, Status.ACTIVE));
    }

    @Test
    void saveCard() throws Exception {
        mockMvc.perform(post("/cards/create")
                        .header("Authorization", token)
                        .content(mapper.writeValueAsString(saveCard))
                        .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }
}
