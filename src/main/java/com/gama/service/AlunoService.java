package com.gama.service;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Aluno;
import com.gama.model.Curso;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        aluno.setMatricula(gerarMatricula());
        Long matricula = alunoRepository.saveAndFlush(aluno).getMatricula();
        return MessageResponseDTO.createMessageResponse("Aluno, matricula: "+ matricula  +", criado com sucesso");
    }

    public MessageResponseDTO modificar(Long id, Aluno aluno) throws NotFoundException {
        existeId(id);

        Optional<Aluno> alunoBanco = alunoRepository.findById(id);

        aluno.setCursos(cursoService.buscarCursoPorIdAluno(id));
        aluno.setNotas(alunoBanco.get().getNotas());
        aluno.setId(id);
        aluno.setMatricula(alunoBanco.get().getMatricula());
        return MessageResponseDTO.createMessageResponse( "Aluno alterado com sucesso, matricula: " + alunoRepository.save(aluno).getId());
    }

    public MessageResponseDTO apagar(Long id) throws NotFoundException {
        existeId(id);
        alunoRepository.deleteById(id);
        return MessageResponseDTO.createMessageResponse("Aluno, matricula: "+ id +", excluído com sucesso");
    }

    public Optional<Aluno> buscarId(Long id) throws NotFoundException {
        existeId(id);
        return alunoRepository.findById(id);
    }

    public Optional<Aluno> buscarMatricula(Long matricula) throws NotFoundException {
        if(!alunoRepository.existsByMatricula(matricula)){
            throw new NotFoundException("Matricula não localizada");
        }
        return alunoRepository.findByMatricula(matricula);
    }


    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public MessageResponseDTO cadastrarAlunoCurso (Long idAluno, Long idCurso) throws NotFoundException, DuplicateException {
        if(buscarId(idAluno).get().getCursos().size() > 0)
            throw new DuplicateException("Aluno já possui um curso cadastrado");
        Curso curso = cursoService.buscarId(idCurso).get();

        Aluno aluno = buscarId(idAluno).get();
        aluno.getCursos().add(curso);

        alunoRepository.save(aluno);
        return MessageResponseDTO.createMessageResponse("Curso adicionado com sucesso");
    }

    public MessageResponseDTO apagarAlunoCurso (Long idAluno, Long idCurso) throws NotFoundException{
        if(buscarId(idAluno).get().getCursos().size() < 1)
            throw new NotFoundException("Aluno não possui um curso cadastrado");
        cursoService.buscarId(idCurso).get();

        Aluno aluno = buscarId(idAluno).get();
        aluno.getCursos().clear();

        alunoRepository.save(aluno);
        return MessageResponseDTO.createMessageResponse("Curso excluído de aluno com sucesso");
    }


    public void existeId(Long id) throws NotFoundException {
        if (!alunoRepository.existsById(id))
            throw new NotFoundException("Aluno não localizado");
    }

    private Long gerarMatricula() {
        Long matricula = (long) LocalDate.now().getYear() * 10000;
        matricula += alunoRepository.count() + 1;
        while (alunoRepository.existsByMatricula(matricula)) {
            matricula += 1;
        }
        return matricula;
    }

}
