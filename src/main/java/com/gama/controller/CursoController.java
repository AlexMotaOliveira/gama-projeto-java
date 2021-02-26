package com.gama.controller;


import com.gama.model.Curso;
import com.gama.model.Disciplina;
import com.gama.service.CursoService;
import com.gama.service.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/curso")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoController {


    public CursoService cursoService;
    public DisciplinaService disciplinaService;


    @PostMapping
    public Curso salvarCurso (@RequestBody Curso curso){
        return cursoService.salvar(curso);
    }


    @GetMapping("/{id}")
    public Optional<Curso> buscarId(@PathVariable Long id){
        return cursoService.buscarId(id);
    }

    @DeleteMapping("/{id}")
    public void apagarCurso (@PathVariable Long id){
        cursoService.apagar(id);
    }

    @GetMapping
    public List<Curso> listarAll(){
        return cursoService.listAll();
    }




    @PostMapping("/{id}/disciplinas")
    public void saveDisciplina (@PathVariable Long id, @RequestBody Disciplina disciplina){
        Optional<Curso> curso = cursoService.buscarId(id);
        if (!curso.isPresent()) {
        }

        curso.get().getDisciplinas().add(disciplinaService.salvar(disciplina));
        cursoService.salvar(curso.get());
    }
}
