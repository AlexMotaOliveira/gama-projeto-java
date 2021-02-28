package com.gama.repository;

import com.gama.model.Aluno;
import com.gama.model.Curso;
import com.gama.model.Disciplina;
import com.gama.model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

    @Modifying
    @Query(value = "insert into aluno_notas (aluno_id, notas_id) values (?1, ?2)", nativeQuery = true)
    void inserirRelacionamento (Long aluno_id, Long notas_id);

    @Query(value="select n.id,n.tipo_nota,n.valor_nota from notas n inner join aluno_notas an on an.notas_id = n.id inner join notas_disciplinas nd on nd.notas_id = n.id where an.aluno_id =?1 and nd.disciplinas_id=?2", nativeQuery=true)
    List<Notas> buscarNotasPorIdAluno(Long aluno_id,Long disciplina_id);

    @Query(value="DELETE FROM  ALUNO_NOTAS WHERE NOTAS_ID=?1 AND ALUNO_ID=?2", nativeQuery=true)
    List<Notas> excluirRelacionamentoNotasDisciplinas(Long notas_id,Long disciplinas_id);


}
