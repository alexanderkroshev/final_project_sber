package client.controller;

import client.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.dto.AuthDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @MockBean
    private AuthService authService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void login() {
        AuthDto authDto = new AuthDto("12345829896782", "1111");
        String json = new ObjectMapper().writeValueAsString(authDto);
        mockMvc.perform(post("/cards/login")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void logout() {
        mockMvc.perform(post("/cards/logout")
        ).andExpect(status().isOk());
    }
}

