package com.estudos.Vagas.Controller;


import com.estudos.Vagas.Dto.VagasDto;
import com.estudos.Vagas.Model.VagasModel;
import com.estudos.Vagas.Service.VagasService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagasController {

    private final VagasService vagasService;

    public VagasController(VagasService vagasService) {
        this.vagasService = vagasService;
    }

    @PostMapping
    public ResponseEntity<VagasModel> salvar(@RequestBody @Valid VagasDto dto, UriComponentsBuilder uriBuilder) {
        var vaga = vagasService.salvar(dto);
        //O Spring injeta automaticamente no metodo
        //Ele ja sabe a URL base da aplicaçao (localhost ou dominio em prod)
        var uri = uriBuilder.path("/vagas/{id}")//Define o caminho da request com placeholder
                .buildAndExpand(vaga.getId())//substitui {id} pelo id real da vaga salva
                .toUri();//conveter para objeto uri
        return ResponseEntity.created(uri).body(vaga);
        //created(uri) = status 201 + header Location com a uri
        //body(vaga) = corpo da resposta com os dados da vaga
    }

    @GetMapping
    public ResponseEntity<List<VagasModel>> listar() {
        return ResponseEntity.ok(vagasService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagasModel> acharPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vagasService.acharPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagasModel> atualizarPorId(@PathVariable Long id, @RequestBody VagasDto dto) {
        return ResponseEntity.ok(vagasService.atualizarPorId(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        vagasService.deletarPorId(id);
        //noContent cria um objeto e o build constroi um objeto para o responseEntity
        return ResponseEntity.noContent().build();
    }
}
