package com.gama.service;

import com.gama.exception.web.NotFoundException;
import com.gama.model.Disciplina;
import com.gama.model.Notas;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.repository.NotasRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        existeDisciplina(notas);

        Long idNotas = notasRepository.saveAndFlush(notas).getId();
        notasRepository.inserirRelacionamento(idAluno, idNotas);

        return MessageResponseDTO.createMessageResponse(idAluno, "Nota Adicionada");
    }

    public MessageResponseDTO modificarNota(Long idAluno, Notas notas) throws NotFoundException {
        alunoService.existeId(idAluno);
        existeDisciplina(notas);

        Long idNotas = notasRepository.saveAndFlush(notas).getId();
        notasRepository.inserirRelacionamento(idAluno, idNotas);

        return MessageResponseDTO.createMessageResponse(idAluno, "Nota Adicionada");
    }

    public Notas existeDisciplina(Notas notas) throws NotFoundException {

        Disciplina disciplina = notas.getDisciplinas().get(0);
        Disciplina disciplinaBD = disciplinaService.existeCodigoDisciplina(disciplina.getCodigo());
        if (!disciplinaBD.getCodigo().equals(disciplina.getCodigo())) {
            throw new NotFoundException("Disciplina não localizada");
        }
        notas.getDisciplinas().get(0).setId(disciplinaBD.getId());
        notas.getDisciplinas().get(0).setConceito(disciplinaBD.getConceito());
        notas.getDisciplinas().get(0).setDisciplina(disciplinaBD.getDisciplina());

        return notas;
    }


}