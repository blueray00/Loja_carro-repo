package br.org.edu.ifrn.lojacarro.security;

import br.org.edu.ifrn.lojacarro.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void deveNegarSemToken() throws Exception {

        mockMvc.perform(get("/carro"))
                .andExpect(status().isForbidden());
    }

    @Test
    void deveNegarTokenInvalido() throws Exception {

        mockMvc.perform(get("/carro")
                        .header("Authorization", "Bearer abc123"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void deveAceitarTokenValido() throws Exception {

        LoginRequest login = new LoginRequest();
        login.setUsername("gerente");
        login.setPassword("123");

        String token = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(content().string(not(isEmptyString())))
                .andReturn()
                .getResponse()
                .getContentAsString();

        mockMvc.perform(get("/carro")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }
}