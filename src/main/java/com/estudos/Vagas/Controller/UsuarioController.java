package com.estudos.Vagas.Controller;
import com.estudos.Vagas.Dto.UsuarioDto;
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

    @PostMapping("/login")
    public ResponseEntity<Void> efetuarLogin(@RequestBody @Valid UsuarioDto dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
