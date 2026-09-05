package com.estudos.Vagas.Repository;

import com.estudos.Vagas.Dto.UsuarioDto;
import com.estudos.Vagas.Model.UsuarioModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    //dando descricao ao bloco de teste
    @DisplayName("Should get user successfully from DB")
    void findByLoginSucess() {
        String login = "henriquedev@email.com";
        UsuarioDto dto = new UsuarioDto(login, "1234");
        //chamando o metodo de salvar
        this.createUser(dto);
        Optional<UsuarioModel> result = this.usuarioRepository.findByLogin(login);
        //verifica se foi salvo
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    //dando descricao ao bloco de teste
    @DisplayName("Should not get user from DB when user not exists")
    void findByLoginNotExists() {
        String login = "henriquedev@email.com";
        Optional<UsuarioModel> result = this.usuarioRepository.findByLogin(login);
        //verifica se usuario nao existe
        assertThat(result.isEmpty()).isTrue();
    }


    //metodo para criacao de usuario no banco de dados
    private UsuarioModel createUser(UsuarioDto dto) {
        UsuarioModel user = new UsuarioModel(dto);
        //vai salvar o usuario
        this.entityManager.persist(user);
        return user;
    }
}