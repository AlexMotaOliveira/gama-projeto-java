package com.gama.service;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.ExceptionError500;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Disciplina;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.repository.CursoRepository;
import com.gama.repository.DisciplinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private CursoRepository cursoRepository;

    public MessageResponseDTO salvar(Disciplina disciplina) throws DuplicateException {
        if (existeCodigo(disciplina.getCodigo()))
            throw new DuplicateException("Disciplina já existe");

        return MessageResponseDTO.createMessageResponse(disciplinaRepository.save(disciplina).getId(), "Disciplina cadastrada com sucesso");
    }

    public List<Disciplina> listAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarId(Long idDisciplina) {

        return disciplinaRepository.findById(idDisciplina);
    }

    public boolean existeCodigo(String codigo) {
        return disciplinaRepository.existsByCodigo(codigo);
    }

    public Disciplina existeCodigoDisciplina(String codigo) {

        return disciplinaRepository.findByCodigo(codigo);
    }

    public void apagar(Long id) throws NotFoundException, ExceptionError500 {
        try {
            if (!disciplinaRepository.existsById(id))
                throw new NotFoundException("Disciplina não localizada");
            disciplinaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionError500("Não é permitida a exclusão de uma disciplina com alunos cadastrados");
        }
    }

    public MessageResponseDTO modificar(Long id, Disciplina disciplina) throws NotFoundException, DuplicateException {
        if (!disciplinaRepository.existsById(id))
            throw new NotFoundException("Disciplina não localizada");

        if (buscarId(id).get().getCodigo().equals(disciplina.getCodigo())) {
            disciplina.setId(id);
            return MessageResponseDTO.createMessageResponse(disciplinaRepository.save(disciplina).getId(), "Disciplina modificada com sucesso");
        }
        if (existeCodigo(disciplina.getCodigo())) {
            throw new DuplicateException("Esse código já está em uso");
        }

        disciplina.setId(id);
        return MessageResponseDTO.createMessageResponse(disciplinaRepository.save(disciplina).getId(), "Disciplina modificada com sucesso");
    }


    @Transactional
    public MessageResponseDTO relacionarDisciplinaCurso(Long idDisciplina, Long idCurso) throws NotFoundException, DuplicateException {
        if (!disciplinaRepository.existsById(idDisciplina) || !cursoRepository.existsById(idCurso))
            throw new NotFoundException("Disciplina/Curso não localizada");

        List<Disciplina> disciplinas = cursoRepository.findById(idCurso).get().getDisciplinas();
        for (Disciplina d : disciplinas) {
            if (d.getId().equals(idDisciplina))
                throw new DuplicateException("Disciplina já cadastrada para este curso");
        }

        disciplinaRepository.inserirRelacionamento(idCurso, idDisciplina);
        return MessageResponseDTO.createMessageResponse(idDisciplina, "Disciplina cadastrada com sucesso!");
    }

    @Transactional
    public MessageResponseDTO apagarRelacionamento(Long disciplinas_id, Long curso_id) throws NotFoundException {
        if (!disciplinaRepository.existsById(disciplinas_id) || !cursoRepository.existsById(curso_id))
            throw new NotFoundException("Disciplina/Curso não localizada");
        disciplinaRepository.removerRelacionamento(curso_id, disciplinas_id);
        return MessageResponseDTO.createMessageResponse(disciplinas_id, "Disciplina excluída com sucesso!");
    }
}