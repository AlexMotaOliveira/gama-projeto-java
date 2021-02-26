package com.gama.service;

import com.gama.model.Aluno;
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

    public Aluno criar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public Aluno modificar(Long id, Aluno aluno){
        aluno.setCursos(cursoService.buscarCursoPorIdAluno(id));
        aluno.setEndereco(alunoRepository.findById(id).get().getEndereco());
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    public void apagar(Long id){
        alunoRepository.deleteById(id);
    }

    public Optional<Aluno> buscarId(Long id){
        return alunoRepository.findById(id);
    }


    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }



    private boolean validaId (Long id){
        return alunoRepository.existsById(id);
    }
}
