package com.gama.controller;


import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Aluno;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.AlunoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aluno")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {

    public AlunoService alunoService;

    @ApiOperation(value = "Criar um Aluno")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cria um Aluno"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO criarAluno (@RequestBody @Valid Aluno aluno) throws DuplicateException {
            return alunoService.criar(aluno);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponseDTO deletarAlunoId (@PathVariable Long id) throws NotFoundException {
        return  alunoService.apagar(id);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponseDTO modificarAluno (@PathVariable Long id, @RequestBody @Valid Aluno aluno) throws NotFoundException {
        return alunoService.modificar(id, aluno);
    }

    @GetMapping({"/{id}"})
    public Optional<Aluno> buscarAlunoId (@PathVariable Long id) throws NotFoundException {
        return  alunoService.buscarId(id);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodosAlunos (){
        return  ResponseEntity.ok(alunoService.listarTodos());
    }
}
