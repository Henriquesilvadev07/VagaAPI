package com.estudos.Vagas.Service;


import com.estudos.Vagas.Dto.VagasDto;
import com.estudos.Vagas.Model.VagasModel;
import com.estudos.Vagas.Repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagasService {


    private final VagaRepository vagaRepository;

    public VagasService(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }


    public VagasModel salvar(VagasDto dto) {
        VagasModel vaga = new VagasModel();

        vaga.setEmpresa(dto.empresa());
        vaga.setTitulo(dto.titulo());
        vaga.setSalario(dto.salario());
        vaga.setModalidade(dto.modalidade());
        vaga.setStatus(dto.status());

        return vagaRepository.save(vaga);
    }

    public VagasModel acharPorId(Long id) {
        return vagaRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Id nåo existe no banco de dados"));
    }

    public List<VagasModel> listar(){
        return vagaRepository.findAll();
    }

    public VagasModel atualizarPorId(Long id, VagasDto dto) {
        VagasModel vaga = vagaRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Id nao encontrado no banco de dados"));
        vaga.setTitulo(dto.titulo());
        vaga.setEmpresa(dto.empresa());
        vaga.setModalidade(dto.modalidade());
        vaga.setSalario(dto.salario());
        vaga.setStatus(dto.status());

        return vagaRepository.saveAndFlush(vaga);
    }

    public void deletarPorId(Long id) {
        if (vagaRepository.existsById(id)){
            vagaRepository.deleteById(id);
        }else {
            System.out.println("Id Nao encontrado no banco de dados");
        }
    }
}
