package com.estudos.Vagas.Security;

import com.estudos.Vagas.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//classe do spring que garante que sera executada uma vez por requisicao
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;


    //sempre que chegar requisicao, o spring vai chamar este metodo do filter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //metodo auxiliar pra vasculhar a requisicao e tentar encontrar o token
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            //o TokenService decodifica a String do token e vai verificar se esta valida e devolver o usuario
            var subject = tokenService.getSubject(tokenJWT);



        }

        //filterChain é a cadeia de filtros//doFilter continuara o fluxo da requisicao
        //necessários para chamar os proximos filtros na aplicacao
        filterChain.doFilter(request, response);

    }

    private String recuperarToken(HttpServletRequest request) {
        //Vai ate a requisicao http e procura o cabechalho/header chamado Authorization
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            //vai retirar a palavra Bearer que vem junto do token e o trim retira os espacos em branco, devolve so o hash limpo
            return authorizationHeader.replace("Bearer", "").trim();
        }

        return null;
    }
}
