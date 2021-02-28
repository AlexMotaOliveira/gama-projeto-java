package com.gama.repository;

import com.gama.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    boolean existsByCodigo(String codigo);

    Disciplina findByCodigo(String codigo);
}
