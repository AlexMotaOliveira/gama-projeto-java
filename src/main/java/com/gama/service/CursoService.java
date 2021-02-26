package com.gama.service;

import com.gama.model.Curso;
import com.gama.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CursoService {

    private CursoRepository cursoRepository;

    public Curso salvar(Curso curso){
        return cursoRepository.save(curso);
    }

    public  List<Curso> buscarCursoPorIdAluno(Long id){
        return cursoRepository.listaCursos(id);
    }

    public Optional<Curso> buscarId(Long id){
        return cursoRepository.findById(id);
    }

    public List<Curso> listAll() {
        return cursoRepository.findAll();
    }

    public void apagar(Long id) {
        cursoRepository.deleteById(id);
    }
}
