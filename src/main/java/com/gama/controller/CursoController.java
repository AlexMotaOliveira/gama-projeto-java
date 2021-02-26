package com.gama.controller;


import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Curso;
import com.gama.model.Disciplina;
import com.gama.model.dto.response.MessageResponseDTO;
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
    public MessageResponseDTO salvarCurso (@RequestBody Curso curso) throws DuplicateException {
        return cursoService.salvarCurso(curso);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO modificarCurso (@PathVariable Long id, @RequestBody Curso curso) throws NotFoundException {
        return cursoService.modificarCurso(id, curso);
    }


    @GetMapping("/{id}")
    public Optional<Curso> buscarCursoId(@PathVariable Long id) throws NotFoundException {
        return cursoService.buscarId(id);
    }

    @DeleteMapping("/{id}")
    public void apagarCursoId (@PathVariable Long id){
        cursoService.apagar(id);
    }

    @GetMapping
    public List<Curso> listarTodosCursos(){
        return cursoService.listAll();
    }




    @PostMapping("/{id}/disciplinas")
    public void saveDisciplina (@PathVariable Long id, @RequestBody Disciplina disciplina) throws DuplicateException, NotFoundException {
        Optional<Curso> curso = cursoService.buscarId(id);
        if (!curso.isPresent()) {
        }

        curso.get().getDisciplinas().add(disciplinaService.salvar(disciplina));
        cursoService.salvarCurso(curso.get());
    }
}
