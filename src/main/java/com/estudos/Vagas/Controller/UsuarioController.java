package com.estudos.Vagas.Controller;
import com.estudos.Vagas.Dto.TokenJwtDto;
import com.estudos.Vagas.Dto.UsuarioDto;
import com.estudos.Vagas.Model.UsuarioModel;
import com.estudos.Vagas.Service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final AuthenticationManager manager;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioDto dto) {
        // Cria um "cartão de identificação" provisório com o login e a senha que o usuário digitou no Postman (dados brutos).
        // Esse objeto ainda NÃO está autenticado, ele serve apenas para carregar as credenciais digitadas.
        var Authtoken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        // Dispara a mágica invisível! O 'manager' (AuthenticationManager) recebe o cartão provisório e faz a validação completa:
        // a)Chama o 'loadUserByUsername' para buscar o usuário e o hash da senha no banco de dados.
        // b) Pega o 'BCryptPasswordEncoder' para comparar a senha digitada (dto.senha()) com o hash do banco.
        // Se a senha estiver certa, ele retorna o objeto 'authentication' preenchido e autenticado. Se estiver errada, joga um erro aqui mesmo.
        var authentication = manager.authenticate(Authtoken);
        var tokenJWT = tokenService.gerarToken((UsuarioModel) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(tokenJWT));
    }
}
