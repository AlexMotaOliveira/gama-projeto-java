package com.gama.repository;

import com.gama.model.Curso;
import com.gama.model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

    @Modifying
    @Query(value = "insert into aluno_notas (aluno_id, notas_id) values (?1, ?2)", nativeQuery = true)
    void inserirRelacionamento (Long aluno_id, Long notas_id);
    
}
