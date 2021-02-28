package com.gama.controller;


import com.gama.exception.web.NotFoundException;
import com.gama.model.Notas;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.service.NotasService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasController {


    private NotasService notasService;

    @PostMapping("/alunos/{idAluno}")
    public MessageResponseDTO salvarDisciplina (@PathVariable Long idAluno, @RequestBody Notas notas) throws NotFoundException {
        return notasService.salvar(idAluno, notas);
    }

    @DeleteMapping("/alunos/{idAluno}")
    public MessageResponseDTO deletarDisciplina (@PathVariable Long idAluno, @RequestBody Notas notas) throws NotFoundException {
        return notasService.modificarNota(idAluno, notas);
    }


}
