package com.estudos.Vagas.Dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(@NotBlank(message = "é necessário colocar o login") String login,
                         @NotBlank(message = "é necessário colocar a senha") String senha) {
}
