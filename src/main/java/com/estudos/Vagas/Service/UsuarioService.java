package com.estudos.Vagas.Service;

import com.estudos.Vagas.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // @Override indica que está sobrescrevendo o métoodo padrão da interface UserDetailsService do Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // busca no banco de dados o usuário pelo "login" (que o Spring chama de username).
        // Como a Model implementa UserDetails, pode receber o retorno direto nessa interface.
        UserDetails user = usuarioRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário Nao encontrado");
        }

        // se passou pela validação (o usuário existe), devolve o objeto 'user' com todos os dados (login, senha, permissões).
        // O Spring Security vai pegar esse retorno e validar a senha (BCrypt) por baixo dos panos.
        return user;
    }
}