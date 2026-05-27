package com.estudos.Vagas.Dto;

import com.estudos.Vagas.Model.ModalidadeEnum;
import com.estudos.Vagas.Model.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record VagasDto(@NotBlank(message = "O titulo é obrigatorio") String titulo,
                       @NotBlank(message = "O nome é obrigatorio") String empresa,
                       @NotNull(message = "O salario é obrigatorio") BigDecimal salario,
                       @NotNull(message = "A modalidade é obrigatoria")  ModalidadeEnum modalidade,
                       @NotNull(message = "O status é obrigatorio") StatusEnum status) {
}
