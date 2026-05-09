package com.estudos.Vagas.Controller;


import com.estudos.Vagas.Dto.VagasDto;
import com.estudos.Vagas.Model.VagasModel;
import com.estudos.Vagas.Service.VagasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagasController {

    private final VagasService vagasService;

    public VagasController(VagasService vagasService) {
        this.vagasService = vagasService;
    }

    @PostMapping
    public ResponseEntity<VagasModel> salvar(@RequestBody VagasDto dto) {
        return ResponseEntity.ok(vagasService.salvar(dto));
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
        return ResponseEntity.noContent().build();
    }
}
