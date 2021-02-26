package com.gama.controller;


import com.gama.exception.web.DuplicateUserException;
import com.gama.exception.web.UserNotFoundException;
import com.gama.model.Aluno;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aluno")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {

    public AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO criarAluno (@RequestBody Aluno aluno) throws DuplicateUserException {
            return alunoService.criar(aluno);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deletar (@PathVariable Long id) throws UserNotFoundException {
        alunoService.apagar(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping({"{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponseDTO modificar (@PathVariable Long id, @RequestBody Aluno aluno) throws UserNotFoundException {
        return alunoService.modificar(id, aluno);
    }

    @GetMapping({"{id}"})
    public Optional<Aluno> buscarId (@PathVariable Long id) throws UserNotFoundException {
        return  alunoService.buscarId(id);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos (){
        return  ResponseEntity.ok(alunoService.listarTodos());
    }
}
