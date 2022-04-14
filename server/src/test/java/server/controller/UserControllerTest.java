package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Type;
import common.dto.AuthDto;
import common.dto.TokenDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private JwtTokenProvider tokenProvider;

    private final String token = "awdfwe4fegb54gh45hg453t";

    AuthDto authDto = new AuthDto("Bob", "$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7", Type.PERSON);

    @MockBean
    private UserRepository userRepository;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setUpMocks() {

//        Mockito.when(userRepository.findByLogin(Mockito.any())).thenReturn(
//                new User(1L, authDto.getLogin(),
//                        authDto.getPassword(),
//                        "Bob", "", Role.ADMIN, Status.ACTIVE));
    }

    @Test
    void saveUser() throws Exception {
        User user = new User();
        user.setLogin("Bobgff");
        user.setPassword("$2a$12$L53hZMAEtZqo2IBBqnxTfOIYrX9abonFc6D3h1g7");
        user.setName("test");
        user.setSurname("test");

       // Mockito.when(authService.login(authDto)).thenReturn(ResponseEntity.ok(new TokenDto(token)));
        Mockito.when(tokenProvider.resolveToken(Mockito.any())).thenReturn(token);
        Mockito.when(tokenProvider.validateToken(token)).thenReturn(true);
        Mockito.when(tokenProvider.getAuthentication(token)).thenReturn(
                new UsernamePasswordAuthenticationToken(authDto.getLogin(), "1111"));

      //  String token = authService.login(authDto).getBody().getToken();
        mockMvc.perform(
                post("/user/create")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user))
        ).andExpect(status().isOk());
    }
}

/*
    @Test
        //TODO move to userController
    void userControllerSaveUser() throws Exception {
        mockMvc.perform(
                post("/api/v1/auth/login")
                        .content(mapper.writeValueAsString(authDto))
                        .contentType(MediaType.APPLICATION_JSON));

        //authService.login(authDto);
        User user = new User();
        user.setLogin("Bob_10");
        user.setPassword("1111");
        user.setName("test");
        user.setSurname("test");

        mockMvc.perform(
                post("/user/create")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user))
        ).andExpect(status().isOk());

        //   Mockito.verify(userService, Mockito.times(1)).saveUser(user);
    }*/