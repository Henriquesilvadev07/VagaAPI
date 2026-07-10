package com.estudos.Vagas.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Indicar ao spring que vamos personalizar as configuracoes de segurança
public class SecurityConfig {

    @Bean //Serve para expor o retorno do metodo, sem a anotacao o spring ignora as configs
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Desativa a proteção contra CSRF (porque o JWT já protege a API)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Define que a API é STATELESS (não salva sessões no servidor)
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Abre o bloco de regras para as rotas/URLs
                .authorizeHttpRequests(req -> req
                        // Libera totalmente a rota de /login (pública)
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        // Bloqueia qualquer outra rota (exige autenticação)
                        .anyRequest().authenticated()
                )
                // 4. Junta tudo e controi a nossa barreira de segurança
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
