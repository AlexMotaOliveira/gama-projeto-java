package com.gama.controller;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Notas;
import com.gama.model.dto.AlunoDisciplinaNotasDTO;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.NotasService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasController {

    private NotasService notasService;

    @PostMapping("/alunos/{idAluno}")
    public MessageResponseDTO salvarNotasDisciplina(@PathVariable Long idAluno, @RequestBody @Valid Notas notas) throws NotFoundException, DuplicateException {
        return notasService.salvar(idAluno, notas);
    }

    @DeleteMapping("/alunos/{idAluno}")
    public MessageResponseDTO deletarNotasDisciplina(@PathVariable Long idAluno, @RequestBody Notas notas) throws NotFoundException {
        return notasService.excluirNota(idAluno, notas);
    }

    @PutMapping("/alunos/{idAluno}")
    public MessageResponseDTO modificarNotasDisciplina(@PathVariable Long idAluno, @RequestBody @Valid Notas notas) throws NotFoundException {
        return notasService.modificarNota(idAluno, notas);
    }

    @GetMapping("/alunos/{idAluno}")
    public AlunoDisciplinaNotasDTO buscaNotasDisciplina(@PathVariable Long idAluno) throws NotFoundException {
        return notasService.buscarId(idAluno);
    }

    @GetMapping("/alunos")
    public List<AlunoDisciplinaNotasDTO> todasNotasDisciplina() throws NotFoundException {
        return notasService.listarTodos();
    }
}
