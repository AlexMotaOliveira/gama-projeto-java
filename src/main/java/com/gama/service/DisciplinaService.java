package com.gama.service;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Disciplina;
import com.gama.model.dto.response.MessageResponseDTO;
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

    public MessageResponseDTO salvar(Disciplina disciplina) throws DuplicateException {
        if(existeCodigo(disciplina.getCodigo()))
            throw new DuplicateException("Disciplina já existe");

        return MessageResponseDTO
                .createMessageResponse(
                        disciplinaRepository.save(disciplina).getId(), "Disciplina cadastrada com sucesso");
    }

    public List<Disciplina> listAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarId(Long idDisciplina) {

        return disciplinaRepository.findById(idDisciplina);
    }

    public boolean existeCodigo (String codigo){
        return disciplinaRepository.existsByCodigo(codigo);
    }

    public Disciplina existeCodigoDisciplina (String codigo){
        
        return disciplinaRepository.findByCodigo(codigo);
    }

    public void apagar(Long id) throws NotFoundException {
        if(!disciplinaRepository.existsById(id))
            throw new NotFoundException("Disciplina não localizada");
        disciplinaRepository.deleteById(id);
    }

    public MessageResponseDTO modificar(Long id, Disciplina disciplina) throws NotFoundException {
        if(!disciplinaRepository.existsById(id))
            throw new NotFoundException("Disciplina não localizada");

        disciplina.setId(id);
        return MessageResponseDTO
                .createMessageResponse(
                        disciplinaRepository.save(disciplina).getId(), "Disciplina modificada com sucesso");
    }
}