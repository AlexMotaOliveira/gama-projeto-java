package com.gama.controller;


import com.gama.model.Curso;
import com.gama.model.Disciplina;
import com.gama.service.CursoService;
import com.gama.service.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoController {


    public CursoService cursoService;
    public DisciplinaService disciplinaService;


    @PostMapping
    public Curso save (@RequestBody Curso curso){
        return cursoService.salvar(curso);
    }

    @PostMapping("/{id}/disciplinas")
    public Disciplina saveDisciplina (@PathVariable Long id,@RequestBody Disciplina disciplina){
        return disciplinaService.salvar(disciplina);
    }

    @GetMapping
    public List<Curso> listAll(){
        return cursoService.listAll();
    }
}
