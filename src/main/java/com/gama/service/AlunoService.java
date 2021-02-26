package com.gama.service;

import com.gama.exception.web.DuplicateUserException;
import com.gama.exception.web.UserNotFoundException;
import com.gama.model.Aluno;
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

    public MessageResponseDTO  criar(Aluno aluno) throws DuplicateUserException {
        if (alunoRepository.existsByCpfOrEmail(aluno.getCpf(), aluno.getEmail())) {
            throw new DuplicateUserException("CPF ou Email já cadastrado");
        }

        return createMessageResponse(alunoRepository.save(aluno).getId(),"Usuário criado com sucesso");
    }

    public MessageResponseDTO modificar(Long id, Aluno aluno) throws UserNotFoundException {
        validaId(id);

        aluno.setCursos(cursoService.buscarCursoPorIdAluno(id));
        aluno.setId(id);
        return createMessageResponse(alunoRepository.save(aluno).getId(),"Usuário alterado com sucesso");
    }

    public void apagar(Long id) throws UserNotFoundException {
        validaId(id);
        alunoRepository.deleteById(id);
    }

    public Optional<Aluno> buscarId(Long id) throws UserNotFoundException {
        validaId(id);
        return alunoRepository.findById(id);
    }


    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }



    private void validaId (Long id) throws UserNotFoundException {
        if (!alunoRepository.existsById(id))
            throw new UserNotFoundException("Usuário não localizado");
    }


    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }


}
