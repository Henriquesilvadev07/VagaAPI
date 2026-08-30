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
        String senha = "1234";
        UsuarioDto dto = new UsuarioDto(login, senha);
        //chamando o metodo de salvar
        this.createUser(dto);

        Optional<UsuarioModel> result = this.usuarioRepository.findByLogin(login);

        //verifica se foi salvo e funcionou
        assertThat(result.isPresent()).isTrue();
    }

    //metodo para criacao de usuario no banco de dados
    private UsuarioModel createUser(UsuarioDto dto) {
        UsuarioModel user = new UsuarioModel(dto);
        //vai salvar o usuario
        this.entityManager.persist(user);
        return user;
    }
}