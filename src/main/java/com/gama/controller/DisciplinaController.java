package com.gama.controller;


import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.ExceptionError500;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Disciplina;
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
@RequestMapping("/api/v1/disciplina")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DisciplinaController {


    public CursoService cursoService;
    public DisciplinaService disciplinaService;


    @ApiOperation(value = "Criar uma Disciplina")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Disciplina cadastrada com sucesso"),
            @ApiResponse(code = 400, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @PostMapping
    public MessageResponseDTO salvarDisciplina(@RequestBody @Valid Disciplina disciplina)
            throws DuplicateException {
        return disciplinaService.salvar(disciplina);
    }

    @ApiOperation(value = "Modificar uma Disciplina")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Disciplina modificada com sucesso"),
            @ApiResponse(code = 404, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @PutMapping("/{id}")
    public MessageResponseDTO modificarCurso(@PathVariable Long id,
                                             @RequestBody @Valid Disciplina disciplina)
            throws NotFoundException, DuplicateException {
        return disciplinaService.modificar(id, disciplina);
    }

    @ApiOperation(value = "Busca uma Disciplina por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Disciplina buscada"),
            @ApiResponse(code = 404, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @GetMapping("/{id}")
    public Optional<Disciplina> buscarCursoId(@PathVariable Long id) throws NotFoundException {
        return disciplinaService.buscarId(id);
    }


    @ApiOperation(value = "Excluir Disciplina")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Disciplina excluída com sucesso"),
            @ApiResponse(code = 404, message = "Falha nos dados enviados"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagaId(@PathVariable Long id) throws NotFoundException, ExceptionError500 {
        disciplinaService.apagar(id);
    }

    @ApiOperation(value = "Listar Todos os Cursos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todas as Disciplinas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
    })
    @GetMapping
    public List<Disciplina> listarTodasDisciplinas() {
        return disciplinaService.listAll();
    }
    
        @ApiOperation(value = "Cadastrar Disciplina no curso")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Disciplina cadastrada com sucesso!"),
                @ApiResponse(code = 400, message = "Falha nos dados enviados"),
                @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
        })
    @PostMapping("/{idDisciplina}/curso/{idCurso}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO cadastrarDisciplinaCurso(@PathVariable Long idDisciplina,
                                                       @PathVariable Long idCurso)
            throws DuplicateException, NotFoundException {
        return disciplinaService.relacionarDisciplinaCurso(idDisciplina, idCurso);
    }

    @ApiOperation(value = "Cadastrar Disciplina no curso")
            @ApiResponses(value = {
                    @ApiResponse(code = 204, message = "DDisciplina excluída com sucesso!"),
                    @ApiResponse(code = 404, message = "Falha nos dados enviados"),
                    @ApiResponse(code = 500, message = "Foi gerada uma exceção, contate o administrator do sistema"),
            })
    @DeleteMapping("/{idDisciplina}/curso/{idCurso}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponseDTO apagarDisciplinaCurso(@PathVariable Long idDisciplina,
                                                    @PathVariable Long idCurso)
            throws NotFoundException {
        return disciplinaService.apagarRelacionamento(idDisciplina, idCurso);
    }
}
