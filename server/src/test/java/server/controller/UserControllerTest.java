package server.controller;

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
import server.model.User;
import server.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ObjectMapper mapper;

    private final AuthDto authAdminDto = new AuthDto(
            "admin",
            "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu"
           );

    private final String token = "token_123";

    private final User user = new User(
            2L,
            "Bob",
            "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu",
            "test",
            "test",
            Role.ADMIN,
            Status.ACTIVE
    );

    @BeforeEach
    void setUpMocks() {
        Mockito.when(jwtTokenProvider.resolveToken(Mockito.any())).thenReturn(token);
        Mockito.when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        Mockito.when(jwtTokenProvider.getAuthentication(token)).thenReturn(
                new UsernamePasswordAuthenticationToken(authAdminDto.getLogin(), "1111"));
        Mockito.when(userService.findByLogin(Mockito.any())).thenReturn(user);
    }

    @Test
    void saveUser() throws Exception {
        User userSave = new User(4L, "test", "test",
                "", "", Role.USER, Status.ACTIVE);
        mockMvc.perform(
                        post("/user/create")
                                .header("Authorization", token)
                                .content(mapper.writeValueAsString(userSave))
                                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }
}
