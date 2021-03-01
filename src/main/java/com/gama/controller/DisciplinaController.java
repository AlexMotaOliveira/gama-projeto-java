package com.gama.controller;


import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Disciplina;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.CursoService;
import com.gama.service.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/disciplina")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DisciplinaController {


    public CursoService cursoService;
    public DisciplinaService disciplinaService;


    @PostMapping
    public MessageResponseDTO salvarDisciplina(@RequestBody @Valid Disciplina disciplina)
            throws DuplicateException {
        return disciplinaService.salvar(disciplina);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO modificarCurso(@PathVariable Long id,
                                             @RequestBody @Valid Disciplina disciplina)
            throws NotFoundException, DuplicateException {
        return disciplinaService.modificar(id, disciplina);
    }


    @GetMapping("/{id}")
    public Optional<Disciplina> buscarCursoId(@PathVariable Long id) throws NotFoundException {
        return disciplinaService.buscarId(id);
    }

    /* TODO: Corrigir o retorno do chamado */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponseDTO apagaId(@PathVariable Long id) throws NotFoundException {
        disciplinaService.apagar(id);
        return MessageResponseDTO.createMessageResponse(id, "Disciplina excluída com sucesso");
    }

    @GetMapping
    public List<Disciplina> listarTodosCursos() {
        return disciplinaService.listAll();
    }

    @PostMapping("/{idDisciplina}/curso/{idCurso}")
    public MessageResponseDTO cadastrarDisciplinaCurdo(@PathVariable Long idDisciplina,
                                                       @PathVariable Long idCurso)
            throws DuplicateException, NotFoundException {
        return disciplinaService.relacionarDisciplinaCurso(idDisciplina, idCurso);
    }

    @DeleteMapping("/{idDisciplina}/curso/{idCurso}")
    public MessageResponseDTO apagarDisciplinaCurdo(@PathVariable Long idDisciplina,
                                                    @PathVariable Long idCurso)
            throws DuplicateException, NotFoundException {
        return disciplinaService.apagarRelacionamento(idDisciplina, idCurso);
    }
}
