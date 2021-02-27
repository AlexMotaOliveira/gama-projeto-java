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

    @PutMapping("/{idCurso}")
    public MessageResponseDTO modificarCurso (@PathVariable Long idCurso,
                                              @RequestBody Curso curso) throws NotFoundException {
        return cursoService.modificarCurso(idCurso, curso);
    }


    @GetMapping("/{idCurso}")
    public Optional<Curso> buscarCursoId(@PathVariable Long idCurso) throws NotFoundException {
        return cursoService.buscarId(idCurso);
    }

    @DeleteMapping("/{idCurso}")
    public void apagarCursoId (@PathVariable Long idCurso){
        cursoService.apagar(idCurso);
    }

    @GetMapping
    public List<Curso> listarTodosCursos(){
        return cursoService.listAll();
    }




    @PostMapping("/{idCurso}/disciplinas")
    public void salvarDisciplina (@PathVariable Long idCurso,
                                  @RequestBody Disciplina disciplina)
                                        throws DuplicateException, NotFoundException {
        /* TODO: Alterar "feedback" de erro */
        Optional<Curso> curso = cursoService.buscarId(idCurso);
        if(curso.get().getDisciplinas()
                .stream()
                .filter(d -> d.getCodigo().equals(disciplina.getCodigo())).count() > 0){
            throw new DuplicateException("Disciplina já existe");
        }
        curso.get().getDisciplinas().add(disciplinaService.salvar(disciplina));
        cursoService.salvarCursoDsiciplina(curso.get());
    }

    @PutMapping("/{idCurso}/disciplinas/{idDisciplina}")
    public void modificarDisciplina (@PathVariable Long idCurso,
                                     @PathVariable Long idDisciplina,
                                     @RequestBody Disciplina disciplina)
                                            throws  NotFoundException {
        Optional<Curso> curso = cursoService.buscarId(idCurso);
        if (!curso.isPresent());


//        curso.get().getDisciplinas().set(idDisciplina,disciplina);
//        cursoService.salvarCurso(curso.get());
    }

    @GetMapping("/{idCurso}/disciplinas")
    public List<Disciplina> listarTodasDisciplinas(@PathVariable Long idCurso){
        return disciplinaService.listAll();
    }
}
