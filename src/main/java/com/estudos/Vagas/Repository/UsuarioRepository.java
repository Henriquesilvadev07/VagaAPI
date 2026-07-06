package com.estudos.Vagas.Repository;

import com.estudos.Vagas.Users.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UserDetails findByLogin(String login);

}
