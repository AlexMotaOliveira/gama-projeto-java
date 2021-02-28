package com.gama.service;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Aluno;
import com.gama.model.Notas;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoService {

    private AlunoRepository alunoRepository;
    private CursoService cursoService;

    public MessageResponseDTO criarAluno(Aluno aluno) throws DuplicateException {
        if (alunoRepository.existsByCpfOrEmail(aluno.getCpf(), aluno.getEmail())) {
            throw new DuplicateException("CPF ou Email já cadastrado");
        }

        return MessageResponseDTO.createMessageResponse(alunoRepository.saveAndFlush(aluno).getId(),"Usuário criado com sucesso");
    }

    public MessageResponseDTO modificar(Long id, Aluno aluno) throws NotFoundException {
        existeId(id);

        aluno.setCursos(cursoService.buscarCursoPorIdAluno(id));
        aluno.setNotas(alunoRepository.findById(id).get().getNotas());
        aluno.setId(id);
        return MessageResponseDTO.createMessageResponse(alunoRepository.save(aluno).getId(),"Usuário alterado com sucesso");
    }

    public MessageResponseDTO apagar(Long id) throws NotFoundException {
        existeId(id);
        alunoRepository.deleteById(id);
        return MessageResponseDTO.createMessageResponse(id,"Usuário excluído com sucesso");
    }

    public Optional<Aluno> buscarId(Long id) throws NotFoundException {
        existeId(id);
        return alunoRepository.findById(id);
    }


    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }



    public void existeId (Long id) throws NotFoundException {
        if (!alunoRepository.existsById(id))
            throw new NotFoundException("Usuário não localizado");
    }
   
}
