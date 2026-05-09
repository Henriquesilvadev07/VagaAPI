package com.estudos.Vagas.Repository;

import com.estudos.Vagas.Model.VagasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<VagasModel, Long> {
}
