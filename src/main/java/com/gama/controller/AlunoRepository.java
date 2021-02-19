package com.gama.controller;

import com.gama.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {


    public Aluno findAlunoByIdAndCep (Long id, String cep);
}
