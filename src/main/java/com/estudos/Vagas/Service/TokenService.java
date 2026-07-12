package com.estudos.Vagas.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.estudos.Vagas.Model.UsuarioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(UsuarioModel usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    //dono do gerador do JWT
                    .withIssuer("RickDev Java")
                    //login de quem esta solicitando a geracao do JWT
                    .withSubject(usuario.getLogin())
                    //Dizer qual informacao pegar, nesse caso o id do usuario
                    .withClaim("id", usuario.getId())
                    //definindo quando o token vai expirar pegando o horario do metodo dataExpiracao
                    .withExpiresAt(dataExpiracao())
                    //assinatura do toke/senha de protecao
                    .sign(algoritmo);
        }catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    //metodo para gerenciar a expiracao do token
    private Instant dataExpiracao() {
        //dizer que o LocalDateTime vai pegar a hora exata, adicionar duas horas e transformar no modo Instant
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
