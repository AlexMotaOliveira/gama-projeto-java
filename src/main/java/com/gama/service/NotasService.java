package com.gama.service;

import com.gama.exception.web.DuplicateException;
import com.gama.exception.web.NotFoundException;
import com.gama.model.Aluno;
import com.gama.model.Disciplina;
import com.gama.model.Notas;
import com.gama.model.dto.AlunoDisciplinaNotasDTO;
import com.gama.model.dto.DisciplinasDTO;
import com.gama.model.dto.NotasDTO;
import com.gama.model.dto.response.MessageResponseDTO;
import com.gama.repository.NotasRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasService {

    private NotasRepository notasRepository;
    private AlunoService alunoService;
    private DisciplinaService disciplinaService;
    
    @Transactional
    public MessageResponseDTO salvar(Long idAluno, Notas notas) throws NotFoundException, DuplicateException {
        alunoService.existeId(idAluno);
        existeDisciplina(notas);
        List<Notas> notasAluno =notasRepository.buscarNotasPorIdAluno(idAluno,notas.getDisciplinas().get(0).getId());
        for (Notas notas1 : notasAluno){
            if(notas1.getTipoNota().equals(notas.getTipoNota())){
                throw new DuplicateException("Essa nota já foi inserida");
            }
        }

        Long idNotas = notasRepository.saveAndFlush(notas).getId();
        notasRepository.inserirRelacionamento(idAluno, idNotas);

        return MessageResponseDTO.createMessageResponse(idAluno, "Nota Adicionada");
    }

    @Transactional
    public MessageResponseDTO modificarNota(Long idAluno, Notas notas) throws NotFoundException {
        alunoService.existeId(idAluno);
        existeDisciplina(notas);
        List<Notas> notasAluno =notasRepository.buscarNotasPorIdAluno(idAluno,notas.getDisciplinas().get(0).getId());
        for (Notas notas1 : notasAluno){
            if(notas1.getTipoNota().equals(notas.getTipoNota())){
                notas.setId(notas1.getId());
                notasRepository.saveAndFlush(notas);
                return MessageResponseDTO.createMessageResponse(idAluno, "Nota Modificada");
            }
        }
        throw new NotFoundException("Nota não localizada para alteração");
    }

    @Transactional
    public MessageResponseDTO excluirNota(Long idAluno, Notas notas) throws NotFoundException {
        alunoService.existeId(idAluno);
        existeDisciplina(notas);
        List<Notas> notasAluno =notasRepository.buscarNotasPorIdAluno(idAluno,notas.getDisciplinas().get(0).getId());
        for (Notas notas1 : notasAluno){
            if(notas1.getTipoNota().equals(notas.getTipoNota())){
                notasRepository.excluirRelacionamentoNotasDisciplinas(idAluno, notas1.getId());
                notasRepository.deleteById(notas1.getId());
                return MessageResponseDTO.createMessageResponse(idAluno, "Nota excluída");
            }
        }
        throw new NotFoundException("Nota não localizada para exclusão");
    }

    @Transactional
    public AlunoDisciplinaNotasDTO buscarId(Long idAluno) throws NotFoundException {
        alunoService.existeId(idAluno);
        AlunoDisciplinaNotasDTO alunoDisciplinaNotasDTO = new AlunoDisciplinaNotasDTO();

        Aluno aluno = alunoService.buscarId(idAluno).get();

        alunoDisciplinaNotasDTO.setNome(aluno.getNome());
        alunoDisciplinaNotasDTO.setMatricula(aluno.getMatricula());
        alunoDisciplinaNotasDTO.setCurso(aluno.getCursos().get(0).getCurso());


        for (Disciplina d: aluno.getCursos().get(0).getDisciplinas()) {
            DisciplinasDTO disciplina = new DisciplinasDTO();
            disciplina.setCodigo(d.getCodigo());
            disciplina.setDisciplina(d.getDisciplina());
            alunoDisciplinaNotasDTO.getDisciplinas().add(disciplina);
        }

        List<Notas> alunoNotas = aluno.getNotas();
        for (DisciplinasDTO d: alunoDisciplinaNotasDTO.getDisciplinas()){
            for(Notas nota : alunoNotas){
                if(d.getCodigo().equals(nota.getDisciplinas().get(0).getCodigo())){
                    d.getNotas().add(new NotasDTO(nota.getTipoNota(), nota.getValorNota()));
                }
                d.status();
            }
        }

        return alunoDisciplinaNotasDTO ;
    }


    public List<AlunoDisciplinaNotasDTO> listarTodos () throws NotFoundException {
        List<AlunoDisciplinaNotasDTO> alunoDisciplinaNotasDTOS = new ArrayList<>();
        List<Aluno> listaAlunos = alunoService.listarTodos();

        for (Aluno idAluno: listaAlunos) {
            alunoDisciplinaNotasDTOS.add(buscarId(idAluno.getId()));
        }
        return alunoDisciplinaNotasDTOS;
    }



    public Notas existeDisciplina(Notas notas) throws NotFoundException {

        Disciplina disciplina = notas.getDisciplinas().get(0);
        Disciplina disciplinaBD = disciplinaService.existeCodigoDisciplina(disciplina.getCodigo());
        if (disciplinaBD== null || !disciplinaBD.getCodigo().equals(disciplina.getCodigo())) {
            throw new NotFoundException("Disciplina não localizada");
        }
        notas.getDisciplinas().get(0).setId(disciplinaBD.getId());
        notas.getDisciplinas().get(0).setConceito(disciplinaBD.getConceito());
        notas.getDisciplinas().get(0).setDisciplina(disciplinaBD.getDisciplina());

        return notas;
    }


}