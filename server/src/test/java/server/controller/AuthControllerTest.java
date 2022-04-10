package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Type;
import common.dto.AuthDto;
import common.dto.TokenDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import server.Role;
import server.Status;
import server.auth.jwt.JwtTokenProvider;
import server.model.User;
import server.repository.UserRepository;
import server.service.AuthService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @MockBean
    private AuthService authService;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private UserRepository userRepository;

    private String token = "token_123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private AuthDto authDto = new AuthDto("bob", "1111", Type.PERSON);

    @BeforeEach
    void setUpMocks() {
        Mockito.when(authService.login(authDto)).thenReturn(ResponseEntity.ok(new TokenDto(token)));
        Mockito.when(jwtTokenProvider.resolveToken(Mockito.any())).thenReturn(token);
        Mockito.when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        Mockito.when(jwtTokenProvider.getAuthentication(token)).thenReturn(
                new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword())
        );
        Mockito.when(userRepository.findByLogin(Mockito.any())).thenReturn(
                new User(1L, authDto.getLogin(), "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7.BLz2sfzHVHuu", "bob", "", Role.USER, Status.ACTIVE)
        );
    }

    @Test
    @SneakyThrows
    void login() {
        mockMvc.perform(
                post("/api/v1/auth/login")
                        .content(mapper.writeValueAsString(authDto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.token").value(token));
    }

    @Test
    @SneakyThrows
    void logout() {
        mockMvc.perform(
                post("/api/v1/auth/logout")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

}
