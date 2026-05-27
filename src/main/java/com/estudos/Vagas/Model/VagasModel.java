package com.estudos.Vagas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vagas")
public class VagasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String empresa;

    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidade;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

}
