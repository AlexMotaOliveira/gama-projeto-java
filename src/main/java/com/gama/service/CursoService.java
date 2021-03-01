package com.gama.service;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.ExceptionError500;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Curso;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoService {
  
    private CursoRepository cursoRepository;

    public MessageResponseDTO salvarCurso(Curso curso) throws DuplicateException {
        existeCurso(curso.getCodigo());
        return MessageResponseDTO.createMessageResponse(cursoRepository.save(curso).getId(), "Curso salvo com sucesso!");
    }

    public MessageResponseDTO modificarCurso(Long id, Curso curso) throws NotFoundException, DuplicateException {
        existeCurso(curso.getCodigo());
        Curso curso1 = buscarId(id).get();
        curso.getDisciplinas().addAll(curso1.getDisciplinas());
        curso.setId(id);
        return MessageResponseDTO.createMessageResponse(cursoRepository.save(curso).getId(), "Curso alterado com sucesso!");
    }

    public  List<Curso> buscarCursoPorIdAluno(Long id){
        return cursoRepository.listaCursos(id);
    }

    public Optional<Curso> buscarId(Long id) throws NotFoundException {
        existeId(id);
        return cursoRepository.findById(id);
    }

    public List<Curso> listAll() {
        return cursoRepository.findAll();
    }

    public MessageResponseDTO apagar(Long id)  throws ExceptionError500 {
        try {
            cursoRepository.deleteById(id);
        }catch (ConstraintViolationException e){
            return MessageResponseDTO.createMessageResponse( id, "Alunos cadastrados no curso, não é permitada a exclusão");
        }
        return MessageResponseDTO.createMessageResponse( id, "Curso excluído por não ter nenhum aluno cadastrado");
    }


    public MessageResponseDTO salvarCursoDsiciplina(Curso curso) throws DuplicateException {
        return MessageResponseDTO.createMessageResponse(cursoRepository.save(curso).getId(), "Disciplina salva com sucesso!");
    }

    
    
    private void existeCurso (String codigo) throws DuplicateException {
        if (cursoRepository.existsByCodigo(codigo))
            throw new DuplicateException("Código já cadastrado para outro curso");
    }

    private void existeId (Long id) throws NotFoundException {
        if (!cursoRepository.findById(id).isPresent())
            throw new NotFoundException("Curso não localizado");
    }
}
