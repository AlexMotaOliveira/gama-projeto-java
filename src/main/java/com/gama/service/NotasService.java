package com.gama.service;

import com.gama.exception.web.NotFoundException;
import com.gama.model.Curso;
import com.gama.model.Disciplina;
import com.gama.model.Notas;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.repository.NotasRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasService {

    private NotasRepository notasRepository;
    private AlunoService alunoService;
    private DisciplinaService disciplinaService;
    /* TODO: Verificar duplicidade de prova. ex: P1,P1,P1,...*/
    @Transactional
    public MessageResponseDTO salvar(Long idAluno, Notas notas) throws NotFoundException {
            alunoService.existeId(idAluno);
            Disciplina disciplina = notas.getDisciplinas().get(0);
            Disciplina disciplina1 = disciplinaService.existeCodigoDisciplina(disciplina.getCodigo());
        if (!disciplina1.getCodigo().equals(disciplina.getCodigo())) {
            throw new NotFoundException("Disciplina não localizada");
        }

        notas.getDisciplinas().get(0).setId(disciplina1.getId());
        notas.getDisciplinas().get(0).setConceito(disciplina1.getConceito());
        notas.getDisciplinas().get(0).setDisciplina(disciplina1.getDisciplina());
        Long idNotas = notasRepository.saveAndFlush(notas).getId();
        notasRepository.inserirRelacionamento(idAluno, idNotas);

        return MessageResponseDTO.createMessageResponse( idAluno, "Nota Adicionada");
    }



}