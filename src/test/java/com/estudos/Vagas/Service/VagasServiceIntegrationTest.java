package com.estudos.Vagas.Service;

import com.estudos.Vagas.Repository.VagaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VagasServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VagaRepository vagaRepository;

    @Test
    @DisplayName("deve cadastrar uma vaga com sucesso quando os dados forem validos")
    @WithMockUser(username = "operador", roles = {"ROLE_USER"})
    void salvarVagaComSucesso() throws Exception{

        String jsonPayLoad = """
                {
                "titulo" : "Auxiliar de Suporte tecnico",
                "empresa" : "ACRUX gestão",
                "salario" : "2280.00",
                "modalidade" : "PRESENCIAL",
                "status" : "ABERTA"
                }
                """;



    }

}
