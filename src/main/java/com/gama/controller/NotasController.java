package com.gama.controller;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Notas;
import com.gama.model.dto.AlunoDisciplinaNotasDTO;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.NotasService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasController {

    private NotasService notasService;

    @ApiOperation(value = "Criar uma Nota")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nota Adicionada"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/alunos/{idAluno}")
    public MessageResponseDTO salvarNotasDisciplina(@PathVariable Long idAluno, @RequestBody @Valid Notas notas) throws NotFoundException, DuplicateException {
        return notasService.salvar(idAluno, notas);
    }


    @ApiOperation(value = "Excluir uma Nota")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nota excluída"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @DeleteMapping("/alunos/{idAluno}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponseDTO deletarNotasDisciplina(@PathVariable Long idAluno, @RequestBody Notas notas) throws NotFoundException {
        return notasService.excluirNota(idAluno, notas);
    }


    @ApiOperation(value = "Motificar uma nota")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nota Modificada"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @PutMapping("/alunos/{idAluno}")
    public MessageResponseDTO modificarNotasDisciplina(@PathVariable Long idAluno, @RequestBody @Valid Notas notas) throws NotFoundException {
        return notasService.modificarNota(idAluno, notas);
    }


    @ApiOperation(value = "Buscar Notas por Aluno")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notas Dos alunos"),
            @ApiResponse(code = 404, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @GetMapping("/alunos/{idAluno}")
    public AlunoDisciplinaNotasDTO buscaNotasDisciplina(@PathVariable Long idAluno) throws NotFoundException {
        return notasService.buscarId(idAluno);
    }

    @ApiOperation(value = "Buscar Todas as Notas ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todas as notas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @GetMapping("/alunos")
    public List<AlunoDisciplinaNotasDTO> todasNotasDisciplina() throws NotFoundException {
        return notasService.listarTodos();
    }
}
