package com.gama.service;

import com.gama.model.Disciplina;
import com.gama.repository.DisciplinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;

    public Disciplina salvar(Disciplina disciplina){
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarId(Long idDisciplina) {
        return disciplinaRepository.findById(idDisciplina);
    }
}