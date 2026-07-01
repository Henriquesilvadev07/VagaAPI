package com.estudos.Vagas.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorTreatment {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors()
                .stream()//pega a lista de FieldErrors que o spring gerou ao validar o Json com o @Valid
                .map(DadosErroValidacao::new)//transforma cada fielderror em um DadosErroValidacao (so campo e mensagem)
                .toList();//coleta todos os DadosErroValidacao transformados em uma lista
        return ResponseEntity.badRequest().body(erros); //vai fazer o retorno da lista de erros ativos
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErro404(RuntimeException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
