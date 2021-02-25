package main.java.com.gama.controller;

import com.gama.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoCntroller {

    @Autowired
    public AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Aluno> salvar (@RequestBody Aluno aluno){
            return  ResponseEntity.ok(alunoRepository.save(aluno));
    }
}
