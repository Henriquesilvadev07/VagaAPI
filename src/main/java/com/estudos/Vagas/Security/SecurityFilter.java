package com.estudos.Vagas.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//classe do spring que garante que sera executada uma vez por requisicao
@Component
public class SecurityFilter extends OncePerRequestFilter {


    //sempre que chegar requisicao, o spring vai chamar este metodo do filter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //filterChain é a cadeia de filtros//doFilter continuara o fluxo da requisicao
        //necessários para chamar os proximos filtros na aplicacao
        filterChain.doFilter(request, response);





    }
}
