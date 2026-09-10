package com.estudos.Vagas.Service;

import com.estudos.Vagas.Dto.VagasDto;
import com.estudos.Vagas.Model.ModalidadeEnum;
import com.estudos.Vagas.Model.StatusEnum;
import com.estudos.Vagas.Model.VagasModel;
import com.estudos.Vagas.Repository.VagaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.estudos.Vagas.Model.StatusEnum.ABERTA;
import static com.estudos.Vagas.Model.StatusEnum.FECHADA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VagasServiceTest {

    @Mock
    private VagaRepository vagaRepository;

    @InjectMocks
    private VagasService vagasService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should save vagas")
    void salvar() {
        VagasDto dto = new VagasDto(
                "Estagio Java",
                "Vockan",
                BigDecimal.valueOf(1.800),
                ModalidadeEnum.HIBRIDO,
                FECHADA);

        VagasModel vagas = new VagasModel();
        vagas.setTitulo(dto.titulo());
        vagas.setEmpresa(dto.empresa());
        vagas.setSalario(dto.salario());
        vagas.setModalidade(dto.modalidade());
        vagas.setStatus(dto.status());

        when(vagaRepository.save(any(VagasModel.class))).thenReturn(vagas);

        VagasModel vagasSalvas = vagasService.salvar(dto);

        assertNotNull(vagasSalvas);
        assertEquals("Estagio Java", vagasSalvas.getTitulo());
        assertEquals("Vockan", vagasSalvas.getEmpresa());

    }

    @Test
    @DisplayName("Should list all vagas successfully")
    void listar(){

    }


}