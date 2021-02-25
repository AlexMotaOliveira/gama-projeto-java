package com.gama.service;

import com.gama.model.Professor;
import com.gama.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProfessorService {

    private ProfessorRepository professorRepository;

    public Professor creatUser(@RequestBody Professor professor){
        return professorRepository.save(professor);
    }


    public List<Professor> listAll() {
        return professorRepository.findAll();
    }
}
