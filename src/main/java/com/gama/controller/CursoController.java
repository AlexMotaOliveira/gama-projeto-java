package com.gama.controller;


import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.ExceptionError500;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Curso;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.CursoService;
import com.gama.service.DisciplinaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoController {


    public CursoService cursoService;
    public DisciplinaService disciplinaService;

    @ApiOperation(value = "Criar um Curso")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Curso criado com sucesso"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO salvarCurso(@RequestBody @Valid Curso curso) throws DuplicateException {
        return cursoService.salvarCurso(curso);
    }

    @ApiOperation(value = "Modificar um Curso")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Curso modificado com sucesso"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 404, message = "Curso não localizado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),})
    @PutMapping("/{idCurso}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponseDTO modificarCurso(@PathVariable Long idCurso,
                                             @RequestBody @Valid Curso curso) throws NotFoundException, DuplicateException {
        return cursoService.modificarCurso(idCurso, curso);
    }


    @ApiOperation(value = "Buscar um Curso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de curso"),
            @ApiResponse(code = 404, message = "Curso não localizado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),})
    @GetMapping("/{idCurso}")
    public Optional<Curso> buscarCursoId(@PathVariable Long idCurso) throws NotFoundException {
        return cursoService.buscarId(idCurso);
    }

    @ApiOperation(value = "Excluir um Curso")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Curso excluído com sucesso"),
            @ApiResponse(code = 403, message = "Não é permitida a exclusão de um curso com alunos cadastrados"),
            @ApiResponse(code = 404, message = "Curso não localizado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),})
    @DeleteMapping("/{idCurso}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarCursoId(@PathVariable Long idCurso) throws ExceptionError500, NotFoundException {
        cursoService.apagar(idCurso);
    }

    @ApiOperation(value = "Listar um Curso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de curso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),})
    @GetMapping
    public List<Curso> listarTodosCursos() {
        return cursoService.listAll();
    }

}
