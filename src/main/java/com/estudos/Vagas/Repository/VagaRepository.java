package com.estudos.Vagas.Repository;

import com.estudos.Vagas.Model.VagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<VagaEntity, Long> {
}
