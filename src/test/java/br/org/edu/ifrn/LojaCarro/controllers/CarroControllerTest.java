package br.org.edu.ifrn.LojaCarro.controllers;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@WebMvcTest(CarroController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(br.org.edu.ifrn.LojaCarro.security.SecurityConfig.class)
class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarroService carroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "gerente", roles = "GERENTE")
    void deveListarTodos() throws Exception {
        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("Gol");
        carro.setAno(2020);

        Mockito.when(carroService.findAll()).thenReturn(List.of(carro));

        mockMvc.perform(get("/carro"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "gerente", roles = "GERENTE")
    void deveBuscarPorId() throws Exception {
        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("Gol");
        carro.setAno(2020);

        Mockito.when(carroService.findById(1L)).thenReturn(Optional.of(carro));

        mockMvc.perform(get("/carro/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "gerente", roles = "GERENTE")
    void deveSalvarCarro() throws Exception {
        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("Gol");
        carro.setAno(2020);

        Mockito.when(carroService.save(any(Carro.class))).thenReturn(carro);

        mockMvc.perform(post("/carro/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carro)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "gerente", roles = "GERENTE")
    void deveAtualizarCarro() throws Exception {
        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("Gol");
        carro.setAno(2021);

        Mockito.when(carroService.update(any(Carro.class))).thenReturn(carro);

        mockMvc.perform(put("/carro/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carro)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "gerente", roles = "GERENTE")
    void deveDeletarCarro() throws Exception {
        mockMvc.perform(delete("/carro/1"))
                .andExpect(status().isNoContent());
    }
}
