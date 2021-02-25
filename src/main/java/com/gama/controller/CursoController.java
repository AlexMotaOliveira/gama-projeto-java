package com.gama.controller;


import com.gama.model.Aluno;
import com.gama.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/aluno")
public class AlunoController {

    @Autowired
    public AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Aluno> salvar (@RequestBody Aluno aluno){
            return  ResponseEntity.ok(alunoRepository.save(aluno));
    }
}
