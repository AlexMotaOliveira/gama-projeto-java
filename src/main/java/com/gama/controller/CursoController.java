package com.gama.controller;


import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.ExceptionError500;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Curso;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.CursoService;
import com.gama.service.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/curso")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoController {


    public CursoService cursoService;
    public DisciplinaService disciplinaService;


    @PostMapping
    public MessageResponseDTO salvarCurso (@RequestBody @Valid Curso curso) throws DuplicateException {
        return cursoService.salvarCurso(curso);
    }

    @PutMapping("/{idCurso}")
    public MessageResponseDTO modificarCurso (@PathVariable Long idCurso,
                                              @RequestBody @Valid Curso curso) throws NotFoundException, DuplicateException {
        return cursoService.modificarCurso(idCurso, curso);
    }


    @GetMapping("/{idCurso}")
    public Optional<Curso> buscarCursoId(@PathVariable Long idCurso) throws NotFoundException {
        return cursoService.buscarId(idCurso);
    }

    @DeleteMapping("/{idCurso}")
    public void apagarCursoId (@PathVariable Long idCurso) throws ExceptionError500 {
        cursoService.apagar(idCurso);
    }

    @GetMapping
    public List<Curso> listarTodosCursos(){
        return cursoService.listAll();
    }
    
}
