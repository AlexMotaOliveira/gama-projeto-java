package com.gama.repository;

import com.gama.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Boolean  existsByCpfOrEmail(String cpf, String email);

    boolean existsByMatricula(Long matricula);
}
