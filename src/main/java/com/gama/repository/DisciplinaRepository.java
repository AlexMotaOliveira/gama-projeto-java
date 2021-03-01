package com.gama.repository;

import com.gama.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    boolean existsByCodigo(String codigo);

    Disciplina findByCodigo(String codigo);

    @Modifying
    @Query(value = "insert into curso_disciplinas (curso_id, disciplinas_id) values (?1, ?2)", nativeQuery = true)
    void inserirRelacionamento(Long curso_id, Long disciplinas_id);

    @Modifying
    @Query(value = "insert into curso_disciplinas (curso_id, disciplinas_id) values (?1, ?2)", nativeQuery = true)
    void removerRelacionamento(Long curso_id, Long disciplinas_id);
    
}
