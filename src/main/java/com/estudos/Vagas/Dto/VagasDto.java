package com.estudos.Vagas.Dto;

import com.estudos.Vagas.Model.ModalidadeEnum;
import com.estudos.Vagas.Model.StatusEnum;

import java.math.BigDecimal;

public record VagasDto(String titulo,
                       String empresa,
                       BigDecimal salario,
                       ModalidadeEnum modalidade,
                       StatusEnum status) {
}
