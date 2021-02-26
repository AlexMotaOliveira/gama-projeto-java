package com.gama.controller;


import com.gama.model.Aluno;
import com.gama.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aluno")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {


    public AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> salvar (@RequestBody Aluno aluno){
            return  ResponseEntity.ok(alunoService.criar(aluno));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        alunoService.apagar(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping({"{id}"})
    public ResponseEntity<Void> modificar (@PathVariable Long id, @RequestBody Aluno aluno){
                alunoService.modificar(id, aluno);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Optional<Aluno>> buscarId (@PathVariable Long id){
        return  ResponseEntity.ok(alunoService.buscarId(id));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos (){
        return  ResponseEntity.ok(alunoService.listarTodos());
    }

    @PostMapping("/{id}/curso")
    public ResponseEntity<Aluno> salvar1 (@RequestBody Aluno aluno){
        return  ResponseEntity.ok(alunoService.criar(aluno));
    }

    @PostMapping("/{id}/curso/{idCurso}/disciplina")
    public ResponseEntity<Aluno> salvar2 (@RequestBody Aluno aluno){
        return  ResponseEntity.ok(alunoService.criar(aluno));
    }
}
