package com.gama.service;

import com.gama.model.Professor;
import com.gama.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessorService {

    private ProfessorRepository professorRepository;

    public Professor creatUser(Professor professor){
        return professorRepository.save(professor);
    }

    public Professor putUserId(Long id, Professor professor){
        Professor updateProfessor = professorRepository.findById(id).get();
        professor.setIdPessoa(id);
       return professorRepository.save(professor);
    }

    public Optional<Professor> findUserId(Long id){
        return professorRepository.findById(id);
    }


    public void deleteUser(Long matricula) throws Exception{
        professorRepository.deleteById(matricula);
    }


    public List<Professor> listAll() {
        return professorRepository.findAll();
    }
}
