package com.gama.service;

import com.gama.model.Notas;
import com.gama.repository.NotasRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasService {

    private NotasRepository notasRepository;


   /* public List<Notas> buscarNotasPorIdAluno(Long id) {
        return notasRepository.ListaNotas(id);
    }*/
}