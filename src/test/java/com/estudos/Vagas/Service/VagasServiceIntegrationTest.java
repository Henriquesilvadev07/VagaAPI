package com.estudos.Vagas.Service;

import com.estudos.Vagas.Model.ModalidadeEnum;
import com.estudos.Vagas.Model.VagasModel;
import com.estudos.Vagas.Repository.VagaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.estudos.Vagas.Model.StatusEnum.ABERTA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

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
    @WithMockUser(username = "operador", roles = {"USER"})
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

            mockMvc.perform(post("/vagas")
                    .contentType(APPLICATION_JSON)
                    .content(jsonPayLoad))
                    .andExpect(status().isCreated());


            boolean salvoNoBanco = vagaRepository.existsByTitulo("Auxiliar de Suporte tecnico");
            assertTrue(salvoNoBanco, "o cliente deveria estar salvo no banco de dados");

    }

    @Test
    @DisplayName("deve retornar sucesso ao retornar vagas salvas")
    @WithMockUser(username = "operador", roles = {"USER"})
    void listarVagasComSucesso() throws Exception{
        VagasModel vagas = new VagasModel();
        vagas.setTitulo("Estagio Java");
        vagas.setEmpresa("Vockan");
        vagas.setSalario(BigDecimal.valueOf(1800.00));
        vagas.setModalidade(ModalidadeEnum.HIBRIDO);
        vagas.setStatus(ABERTA);
        vagaRepository.save(vagas);

        mockMvc.perform(get("/vagas"))
                .andExpect(status().isOk())
                //jsonPath é o responsavel por fazer a leitura do conteudo do JSON
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))//$ representa o objeto inteiro no JSON
                .andExpect(jsonPath("$[0].titulo").value("Estagio Java"));

    }

    @Test
    @DisplayName("deve retornar sucesso ao procurar vaga com um id valido")
    @WithMockUser(username = "operador", roles = {"USER"})
    void acharPorIdComSucesso() throws Exception {
        VagasModel vagas = new VagasModel();
        vagas.setTitulo("Estagio Java");
        vagas.setEmpresa("Vockan");
        vagas.setSalario(BigDecimal.valueOf(1800.00));
        vagas.setModalidade(ModalidadeEnum.HIBRIDO);
        vagas.setStatus(ABERTA);
        vagaRepository.save(vagas);

        mockMvc.perform(get("/vagas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.empresa").value("Vockan"))
                .andExpect(jsonPath("$.titulo").value("Estagio Java"));

    }

    @Test
    @DisplayName("deve retornar uma exception ao tentar procurar por um id invalido")
    @WithMockUser(username = "operador", roles = {"USER"})
    void acharPorIdComException() throws Exception{

        mockMvc.perform(get("/vagas/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("deve retornar sucesso ao atualizar vaga com um id valido")
    @WithMockUser(username = "operador", roles = {"USER"})
    void atualizarPorIdComSucesso() throws Exception {
        VagasModel vagas = new VagasModel();
        vagas.setTitulo("Estagio Java");
        vagas.setEmpresa("Vockan");
        vagas.setSalario(BigDecimal.valueOf(1800.00));
        vagas.setModalidade(ModalidadeEnum.HIBRIDO);
        vagas.setStatus(ABERTA);
        vagaRepository.save(vagas);

        String jsonPayLoad = """
                {
                "titulo" : "Auxiliar de Suporte tecnico",
                "empresa" : "ACRUX gestão",
                "salario" : "2280.00",
                "modalidade" : "PRESENCIAL",
                "status" : "ABERTA"
                }
                """;

        mockMvc.perform(put("/vagas/" + vagas.getId())
                .contentType(APPLICATION_JSON)
                .content(jsonPayLoad))
                .andExpect(status().isOk());

        VagasModel vagaAtualizada = vagaRepository.findById(vagas.getId()).get();
        assertEquals("Auxiliar de Suporte tecnico", vagaAtualizada.getTitulo());


    }

    @Test
    @DisplayName("deve retornar uma exception ao tentar atualizar por um id invalido")
    @WithMockUser(username = "operador", roles = {"USER"})
    void atualizarPorIdComException() throws Exception {

        mockMvc.perform(put("/vagas/1"))
                .andExpect(status().isNotFound());


    }

    @Test
    @DisplayName("deve retornar sucesso ao deletar vaga por um id valido")
    @WithMockUser(username = "operador", roles = {"USER"})
    void deletarPorIdComSucesso() throws Exception{
        VagasModel vagas = new VagasModel();
        vagas.setTitulo("Estagio Java");
        vagas.setEmpresa("Vockan");
        vagas.setSalario(BigDecimal.valueOf(1800.00));
        vagas.setModalidade(ModalidadeEnum.HIBRIDO);
        vagas.setStatus(ABERTA);
        vagaRepository.save(vagas);

    }

}
